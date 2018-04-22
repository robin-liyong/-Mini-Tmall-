<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
        $(function () {
            $.getJSON('/tmall/res/js/basicTheme.json', function (themeJSON) {
                echarts.registerTheme('basicTheme', themeJSON);
                // 基于准备好的dom，初始化eCharts实例
                var myChart = echarts.init($("#chartDiv")[0], "basicTheme");
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '商城近七日交易额'
                    },
                    tooltip: {},
                    legend: {
                        data: ['总交易额', '女装/内衣交易额', '汽车用品/配件交易额', '其他交易额', '骚气交易额']
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['4/16', '4/17', '4/18', '4/19', '4/20', '4/21', '4/22']
                    },
                    yAxis: {
                        type: "value"
                    },
                    series: [
                        {
                            name: '总交易额',
                            type: 'line',
                            data: [820, 902, 1014, 768, 865, 1103, 1296]
                        },
                        {
                            name: '女装/内衣交易额',
                            type: 'line',
                            data: [94, 182, 263, 84, 96, 220, 296]
                        },
                        {
                            name: '汽车用品/配件交易额',
                            type: 'line',
                            data: [150, 232, 201, 154, 190, 130, 410]
                        },
                        {
                            name: '其他交易额',
                            type: 'line',
                            data: [50, 32, 10, 54, 90, 30, 10]
                        },
                        {
                            name: '骚气交易额',
                            type: 'line',
                            data: [42, 42, 120, 4, 170, 84, 41]
                        },
                    ],
                    itemStyle: {
                        // 阴影的大小
                        shadowBlur: 200,
                        // 阴影水平方向上的偏移
                        shadowOffsetX: 0,
                        // 阴影垂直方向上的偏移
                        shadowOffsetY: -150,
                        // 阴影颜色
                        shadowColor: 'rgba(0, 0, 0, 0.1)'
                    }
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            });
        });
    </script>
</head>
<body>
<div id="chartDiv" style="width: 100%;height: 500px">

</div>
</body>

</html>
