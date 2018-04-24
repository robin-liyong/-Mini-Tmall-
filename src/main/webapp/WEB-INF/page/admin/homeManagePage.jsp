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
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    legend: {
                        data: ['总订单数', '交易完成', '等待买家确认', '等待卖家发货', '等待买家付款']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: []
                    },
                    yAxis: {
                        type: "value"
                    },
                    series: [
                        {
                            name: '总订单数',
                            type: 'line',
                            areaStyle: {normal: {}},
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: []
                        },
                        {
                            name: '交易完成',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {normal: {}},
                            data: []
                        },
                        {
                            name: '等待买家确认',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {normal: {}},
                            data: []
                        },
                        {
                            name: '等待卖家发货',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {normal: {}},
                            data: []
                        },
                        {
                            name: '等待买家付款',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {normal: {}},
                            data: []
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                //异步加载数据
                var json = JSON.parse('${requestScope.jsonObject}');
                myChart.setOption({
                    xAxis: {
                        data: json.dateArray
                    },
                    series: [{
                        name: "总交易额",
                        data: json.orderTotalArray
                    }, {
                        name: "交易完成",
                        data: json.orderUnpaidArray
                    }, {
                        name: "等待买家确认",
                        data: json.orderNotShippedArray
                    }, {
                        name: "等待卖家发货",
                        data: json.orderUnconfirmedArray
                    }, {
                        name: "等待买家付款",
                        data: json.orderSuccessArray
                    }]
                });
            });
        });

        function getChartData(beginDate, endDate) {
            var url = beginDate === "" || endDate === "" ? "" : "";
        }
    </script>
</head>
<body>
<div id="chartDiv" style="width: 100%;height: 500px"></div>
</body>

</html>
