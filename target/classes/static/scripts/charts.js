function fillMetricsList(region, instanceid){
    $.getJSON('/metrics/list/{region}/{instanceId}'.replace("{region}",region).replace("{instanceId}",instanceid), function (data) {
        $.each(data, function() {
            $("#metrics").append($("<option />").val(this.metricName).text(this.metricName));
        });
        $("#metrics").selectpicker('refresh');
    });
}

function showCharts(metricsToShow){
    $(".chart").remove();
    $.each(metricsToShow, function(index, metric) {

        $('#chartsContainer').append('<div class="row chart"><div class="col-md-9" id="{container}"> </div></div>'.replace("{container}", metric));
        sample(metric);
    });

}

function sample(metric){
    $.getJSON('/metrics/eu-central-1/i-70d346cc/{metric}'.replace("{metric}",metric), function (data) {

        var series = [];

        $.each(data, function() {
           series.push([this.timestamp,this.average]);
        });

        $("#"+metric).highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: metric
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'AVG'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },

            series: [{
                name: 'Average ' + metric,
                data: series
            }]
        });
    });
}