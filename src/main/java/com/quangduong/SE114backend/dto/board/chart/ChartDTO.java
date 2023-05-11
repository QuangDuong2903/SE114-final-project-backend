package com.quangduong.SE114backend.dto.board.chart;

import java.util.List;

public class ChartDTO {

    private List<UserAndTaskAmountDTO> chart_1;

    private List<UserAndTaskAmountDTO> chart_2;

    public List<UserAndTaskAmountDTO> getChart_2() {
        return chart_2;
    }

    public void setChart_2(List<UserAndTaskAmountDTO> chart_2) {
        this.chart_2 = chart_2;
    }

    public List<UserAndTaskAmountDTO> getChart_3() {
        return chart_3;
    }

    public void setChart_3(List<UserAndTaskAmountDTO> chart_3) {
        this.chart_3 = chart_3;
    }

    private List<UserAndTaskAmountDTO> chart_3;

    public List<UserAndTaskAmountDTO> getChart_1() {
        return chart_1;
    }

    public void setChart_1(List<UserAndTaskAmountDTO> chart_1) {
        this.chart_1 = chart_1;
    }
}
