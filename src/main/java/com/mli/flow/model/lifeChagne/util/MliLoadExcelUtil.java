package com.mli.flow.model.lifeChagne.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 讀取工具
 */

public class MliLoadExcelUtil {
    /**
     * 從前端上傳的檔案讀取 Excel 資料
     *
     * @param file      Excel檔案
     * @param hasHeader true.存在標題列 / false.不存在標題列 (標題列不會讀取)
     */
    public static List<Object[]> loadExcelFromMultipartFile(MultipartFile file, boolean hasHeader)
            throws IOException, InvalidFormatException {
        try (InputStream inputStream = file.getInputStream()) {
            return readExcel(inputStream, hasHeader);
        }
    }

    /**
     * 從 resources 資料夾讀取 Excel 檔案
     *
     * @param resourcePath Excel檔案 (路徑)
     * @param hasHeader    true.存在標題列 / false.不存在標題列 (標題列不會讀取)
     */
    public static List<Object[]> loadExcelFromResources(String resourcePath, boolean hasHeader)
            throws IOException, InvalidFormatException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return readExcel(inputStream, hasHeader);
        }
    }

    /**
     * 核心讀取邏輯：統一每行長度為整個 sheet 的最大欄數
     */
    private static List<Object[]> readExcel(InputStream inputStream, boolean hasHeader)
            throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<Object[]> data = new ArrayList<>();

        if (sheet.getLastRowNum() < 0) {
            workbook.close();
            return data; // 空表直接回傳
        }

        // 第一階段：找出整個 sheet 的最大欄數（最右邊有值的欄位）
        int maxColumns = 0;
        int startRowIndex = hasHeader ? 1 : 0;

        for (int i = startRowIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // 找出這一行最後有值的儲存格位置（getLastCellNum() 會回傳下一個可能的欄位索引）
            int lastCellNum = row.getLastCellNum();
            if (lastCellNum > maxColumns) {
                maxColumns = lastCellNum;
            }
        }

        // 如果完全沒有資料行，maxColumns 還是 0，直接回傳空 list
        if (maxColumns == 0) {
            workbook.close();
            return data;
        }

        // 第二階段：真正讀取資料，並統一每行長度
        for (int i = startRowIndex; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                // 整行為空 → 補滿 null 的陣列
                Object[] emptyRow = new Object[maxColumns];
                data.add(emptyRow);
                continue;
            }

            Object[] rowData = new Object[maxColumns];

            // 填入現有儲存格的值
            for (int j = 0; j < maxColumns; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                rowData[j] = getCellValue(cell);
            }

            data.add(rowData);
        }

        workbook.close();
        return data;
    }

    /**
     * 取得單一儲存格的值
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellType type = cell.getCellType();
        if (type == CellType.FORMULA) {
            type = cell.getCachedFormulaResultType();
        }

        return switch (type) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue();
                }
                double num = cell.getNumericCellValue();
                if (num == Math.floor(num) && num <= Long.MAX_VALUE && num >= Long.MIN_VALUE) {
                    yield (long) num;
                } else {
                    yield num;
                }
            }
            case BOOLEAN -> cell.getBooleanCellValue();
            case BLANK -> null;
            default -> cell.toString().trim();  // 保險
        };
    }
}
