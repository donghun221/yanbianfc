package com.peernet.mobile.server.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.mysql.jdbc.StringUtils;
import com.nationsky.pub.utils.GsonUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        // Encoder encoder = new Encoder();
        // MultimediaInfo info = encoder.getInfo(new File("C:/Users/宋翔/Downloads/videotest/true.mp4"));
        //
        // System.out.println(GsonUtils.toGson(info));
        //
        // info = encoder.getInfo(new File("C:/Users/宋翔/Downloads/videotest/false.mp4"));
        //
        // System.out.println(GsonUtils.toGson(info));
        //
        // EncodingAttributes attr=new EncodingAttributes();
        // attr.setFormat("mp4");
        // VideoAttributes videoAttr=new VideoAttributes();
        // videoAttr.setBitRate(-1);
        // videoAttr.setFrameRate(25);
        // videoAttr.setCodec("FFMPEG");
        // attr.setVideoAttributes(videoAttr);
        // AudioAttributes audioAttr=new AudioAttributes();
        // audioAttr.setBitRate(-1);
        // audioAttr.setChannels(2);
        // audioAttr.setSamplingRate(44100);
        // audioAttr.setCodec("MP3");
        // attr.setAudioAttributes(audioAttr);
        // encoder.encode(new File("C:/Users/宋翔/Downloads/videotest/false.mp4"), new
        // File("C:/Users/宋翔/Downloads/videotest/new.mp4"), attr);

        // System.out.println(GsonUtils.toGson(importHospital(new
        // File("C:/Users/宋翔/Dropbox/MateNet/Documents/医院数据/test"))));
//        System.out.println(GsonUtils.toGson(importHospital(new File("C:/Users/宋翔/Dropbox/MateNet/Documents/医院数据/模版1"))));

//         System.out.println(GsonUtils.toGson(importArea(new File("C:/Users/宋翔/Dropbox/MateNet/Documents/医院数据"))));
         
        System.out.println(GsonUtils.toGson(new File("C:/Users/宋翔/Dropbox/MateNet/Documents/医院数据/新数据").list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if (name.endsWith(".xls"))
                {
                    return true;
                }
                return false;
            }
        })));
    }
    
    

    public static final List<Hospital> importHospital(File dir) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        // String url = "jdbc:mysql://182.92.129.133:3306/peer_net?useUnicode=true&characterEncoding=UTF-8";
        String url = "jdbc:mysql://localhost:3306/peer_net?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();
        ResultSet rs = null;

        List<Hospital> list = new ArrayList<Hospital>();

        File[] files = dir.listFiles(new FilenameFilter()
        {

            @Override
            public boolean accept(File dir, String name)
            {
                if(!name.endsWith(".xls"))
                {
                    return false;
                }
                return true;
            }
        });
        for(File file: files)
        {
            if(file.isDirectory())
            {
                continue;
            }

            System.out.println("Importing " + file.getName());

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            Workbook workbook = new HSSFWorkbook(bis);
            int sheetCount = workbook.getNumberOfSheets();

            String province = workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue().substring(10);

            System.out.println("select code, id from t_dict where type = '04' and value = '" + province + "'");
            rs = stmt.executeQuery("select code, id from t_dict where type = '04' and value = '" + province + "'");
            rs.next();

            if(province.equals("北京市") || province.equals("上海市") || province.equals("重庆市") || province.equals("天津市"))
            {
                continue;
            }

            String provinceCode = rs.getString(1);
            String provinceId = rs.getString(2);
            province = province + " - " + provinceCode;

            for(int i = 0; i < sheetCount; i++)
            {
                if(i == 0)
                {
                    continue;
                }

                Sheet sheet = workbook.getSheetAt(i);

                String city = sheet.getSheetName().trim();
                String cityName = city;
                String cityCode = null;
                String cityId = null;

                rs = stmt.executeQuery("select code, id from t_dict where type = '05' and value = '" + city + "' and parent_id = " + provinceId);
                rs.next();
                cityCode = rs.getString(1);
                cityId = rs.getString(2);
                city = city + " - " + cityCode;

                String distinct = null;
                String distinctCode = null;
                String distinctId = null;

                System.out.println("Processing at sheet: " + sheet.getSheetName());

                int rowIndex = 0;
                while(true)
                {
                    rowIndex++;

                    if(rowIndex == 0)
                    {
                        continue;
                    }

                    Row row = sheet.getRow(rowIndex);

                    System.out.println("Processing at row: " + rowIndex);

                    if(row == null || (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null && row.getCell(3) == null))
                    {
                        break;
                    }

                    String name = null;
                    String level = null;
                    String addr = null;

                    if(row.getCell(1) != null && row.getCell(2) != null && row.getCell(3) != null)
                    {
                        name = row.getCell(1).getStringCellValue();
                        level = row.getCell(2).getStringCellValue();
                        addr = row.getCell(3).getStringCellValue();
                    }

                    if(name == null)
                    {
                        break;
                    }

                    if(StringUtils.isNullOrEmpty(level) && StringUtils.isNullOrEmpty(addr))
                    {
                        distinct = name;
                        distinct=distinct.trim();
                        if(!distinct.equals(cityName))
                        {
                            rs = stmt.executeQuery("select code, id from t_dict where type = '06' and value = '" + distinct + "' and parent_id = " + cityId);
                            System.out.println("select code, id from t_dict where type = '06' and value = '" + distinct + "' and parent_id = " + cityId);
                            rs.next();
                            distinctCode = rs.getString(1);
                            distinctId = rs.getString(2);
                            distinct = distinct + " - " + distinctCode;
                        }
                        continue;
                    }

                    System.out.println("Current hospital is :" + distinct + " " + name);

                    Hospital hospital = new Hospital();
                    hospital.setName(name);
                    hospital.setLevel(level);
                    hospital.setProvince(province);
                    hospital.setCity(city);
                    hospital.setDistinct(distinct);
                    hospital.setParentId(distinctId);

                    list.add(hospital);
                }

            }
        }

        return list;
    }

    public static final List<Province> importArea(File dir) throws Exception
    {

        List<Province> provinceList = new ArrayList<Province>();

        File[] files = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(!name.endsWith(".xls"))
                {
                    return false;
                }
                return true;
            }
        });

        int provinceId = 4000;
        int cityId = 5000;
        int distinctId = 6000;

        for(File file: files)
        {
            System.out.println("Importing " + file.getName());

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            Workbook workbook = new HSSFWorkbook(bis);

            Sheet sheet = workbook.getSheetAt(0);

            Province province = new Province();
            provinceList.add(province);

            List<City> cityList = new ArrayList<City>();
            province.setChildren(cityList);

            int rowIndex = 0;
            while(true)
            {
                Row row = sheet.getRow(rowIndex);
                rowIndex++;

                if(row == null || row.getCell(0) == null)
                {
                    break;
                }

                Cell cell = row.getCell(0);

                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                String text = cell.getStringCellValue();

                int lastSpace = 0;

                int space1 = text.lastIndexOf(' ');
                int space2 = text.lastIndexOf(' ');

                lastSpace = space1 > space2 ? space1 : space2;
                lastSpace += 1;

                if(text.substring(0, lastSpace).length() == 10)
                {
                    province.setName(text.substring(lastSpace));
                    province.setId(provinceId++);
                }

                if(text.substring(0, lastSpace).length() == 12)
                {
                    City city = new City();
                    city.setName(text.substring(lastSpace));
                    city.setChildren(new ArrayList<Distinct>());
                    city.setId(cityId++);
                    city.setParentId(provinceId - 1);
                    cityList.add(city);
                }

                if(text.substring(0, lastSpace).length() == 14)
                {
                    Distinct distinct = new Distinct();
                    distinct.setName(text.substring(lastSpace));
                    distinct.setId(distinctId++);
                    distinct.setParentId(cityId - 1);
                    cityList.get(cityList.size() - 1).getChildren().add(distinct);
                }

            }

        }

        return provinceList;
    }
}

class Province
{
    private String name;

    private List<City> children;

    private int id;

    public List<City> getChildren()
    {
        return children;
    }

    public void setChildren(List<City> children)
    {
        this.children = children;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}

class City
{
    private String name;

    private List<Distinct> children;

    private int id;

    private int parentId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Distinct> getChildren()
    {
        return children;
    }

    public void setChildren(List<Distinct> children)
    {
        this.children = children;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

}

class Distinct
{
    private String name;

    private int id;

    private int parentId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

}

class Hospital
{
    private String name;

    private String level;

    private String province;

    private String city;

    private String distinct;

    private String parentId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistinct()
    {
        return distinct;
    }

    public void setDistinct(String distinct)
    {
        this.distinct = distinct;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

}