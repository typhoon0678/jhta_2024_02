package practice0d.picross.records;

public enum PaintType {
    FILL, EMPTY, // 확정 상태
    TEMP_FILL, TEMP_EMPTY, // 임시 상태
    FILL_TO_EMPTY, EMPTY_TO_FILL, // 이전 상태_TO_임시 상태

    BLOCK, TEMP_BLOCK, EMPTY_TO_BLOCK, BLOCK_TO_EMPTY // X표시
}
