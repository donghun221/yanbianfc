package com.peernet.mobile.server.admin.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.peernet.mobile.server.admin.common.vo.WorkBookData;

public class WorkBookUtils {
	
	
	
	/**
     * 生成用户列表工作簿
     * 
     * @param list
     * @return
     */
    public final static HSSFWorkbook mkUserListExcel(WorkBookData wbData)
    {
        HSSFWorkbook wb = new HSSFWorkbook();

        String title  = wbData.getTitle();
        // 每人每月的明细表
        HSSFSheet sheet = wb.createSheet(title);

        /*
         * 创建表头
         */
        {
            HSSFRow row = sheet.createRow(0);

            // 定义颜色
            HSSFPalette palette = wb.getCustomPalette();
            palette.setColorAtIndex(HSSFColor.GREY_40_PERCENT.index, (byte)230, (byte)230, (byte)230);
            palette.setColorAtIndex(HSSFColor.BLUE_GREY.index, (byte)48, (byte)84, (byte)150);
            palette.setColorAtIndex(HSSFColor.BLUE.index, (byte)91, (byte)155, (byte)213);
            palette.setColorAtIndex(HSSFColor.DARK_TEAL.index, (byte)31, (byte)78, (byte)120);
            palette.setColorAtIndex(HSSFColor.LIGHT_YELLOW.index, (byte)255, (byte)242, (byte)204);
            palette.setColorAtIndex(HSSFColor.GREY_80_PERCENT.index, (byte)58, (byte)56, (byte)56);
            palette.setColorAtIndex(HSSFColor.ORANGE.index, (byte)198, (byte)89, (byte)17);
            palette.setColorAtIndex(HSSFColor.LIGHT_ORANGE.index, (byte)252, (byte)228, (byte)214);
            palette.setColorAtIndex(HSSFColor.GREEN.index, (byte)84, (byte)130, (byte)53);
            palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte)226, (byte)239, (byte)218);
            // 日期行/工时字体
            HSSFFont workFont = wb.createFont();
            workFont.setFontName("微软雅黑");
            workFont.setColor(HSSFColor.WHITE.index);
            HSSFCellStyle workStyle = wb.createCellStyle();
            workStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
            workStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            workStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
            workStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            workStyle.setTopBorderColor(palette.getColor(HSSFColor.BLUE.index).getIndex());
            workStyle.setRightBorderColor(palette.getColor(HSSFColor.BLUE.index).getIndex());
            workStyle.setBottomBorderColor(palette.getColor(HSSFColor.BLUE.index).getIndex());
            workStyle.setLeftBorderColor(palette.getColor(HSSFColor.BLUE.index).getIndex());
            workStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            workStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            workStyle.setFillForegroundColor(palette.getColor(HSSFColor.BLUE.index).getIndex());
            workStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            workStyle.setFont(workFont);
            
            // 左上角
            HSSFCell firstCell = row.createCell(0);
            firstCell.setCellValue("#");
            firstCell.setCellStyle(workStyle);
            
            //设置表头宽度
           {
	            int[] widthSettings = wbData.getHeadWithArray();
	            int len = widthSettings.length;
	            for(int i=0;i<len;i++)
	            {
	            	sheet.setColumnWidth(i+1, widthSettings[i]);
	            }
            }
            
            // 设置表头字段
            String[] headArray = wbData.getHeadArray();
            int len = headArray.length;
            for(int i=0;i<len;i++)
            {
            	HSSFCell c = row.createCell(i+1);
            	c.setCellValue(headArray[i]);
            	c.setCellStyle(workStyle);
            	
            }
        }

        /*
         * 填充数据
         */
        {
        	
        	// 日期行/工时字体
            HSSFFont workFont = wb.createFont();
            workFont.setFontName("微软雅黑");
            //workFont.setColor(HSSFColor.GREY_80_PERCENT.index);

            // 日期行/工时样式
            HSSFCellStyle workStyle = wb.createCellStyle();
            workStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
            workStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            workStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
            workStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            workStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            workStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            workStyle.setFont(workFont);
            workStyle.setWrapText(true);
        	
        	//int rowIndex = 1;
        	String[][] data = wbData.getDataArray();
        	int rowLen = data.length;
        	for(int i=0;i<rowLen;i++)
        	{
        		HSSFRow row = sheet.createRow(i+1);
        		// 序号
        		HSSFCell firstCell = row.createCell(0);
        		firstCell.setCellValue(i+1);
        		firstCell.setCellStyle(workStyle);
        		
        		String rowData[] = data[i];
        		int cellLen = rowData.length;
        		for(int ci=0;ci<cellLen;ci++)
        		{
        			HSSFCell c = row.createCell(ci+1);
        			c.setCellValue(rowData[ci]);
        			c.setCellStyle(workStyle);
        		}
        	}
        }
        return wb;
    }
}
