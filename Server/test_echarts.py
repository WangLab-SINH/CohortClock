from pyecharts import options as opts
from pyecharts.charts import Pie
from snapshot_selenium import snapshot as driver
from pyecharts.render import make_snapshot
import os
#chromedriver = "/root/anaconda3/lib/python3.7/site-packages/snapshot_selenium/chromedriver"
#os.environ["webdriver.chrome.driver"] = chromedriver
"""
Gallery 使用 pyecharts 1.1.0
参考地址: https://echarts.baidu.com/examples/editor.html?c=pie-doughnut

目前无法实现的功能:

1、迷之颜色映射的问题
"""
x_data = ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"]
y_data = [335, 310, 274, 235, 400]
data_pair = [list(z) for z in zip(x_data, y_data)]
data_pair.sort(key=lambda x: x[1])
def bar_chart() -> Pie:
    c = (
        Pie(init_opts=opts.InitOpts(width="1600px", height="800px", bg_color="#2c343c"))
        .add(
            series_name="访问来源",
            data_pair=data_pair,
            rosetype="radius",
            radius="55%",
            center=["50%", "50%"],
            label_opts=opts.LabelOpts(is_show=False, position="center"),
        )
        .set_global_opts(
            title_opts=opts.TitleOpts(
                title="Customized Pie",
                pos_left="center",
                pos_top="20",
                title_textstyle_opts=opts.TextStyleOpts(color="#fff"),
            ),
            legend_opts=opts.LegendOpts(is_show=False),
        )
        .set_series_opts(
            tooltip_opts=opts.TooltipOpts(
                trigger="item", formatter="{a} <br/>{b}: {c} ({d}%)"
            ),
            label_opts=opts.LabelOpts(color="rgba(255, 255, 255, 0.3)"),
        )
        #.render("customized_pie.html")
    )
    return c
print('draw_end')
make_snapshot(driver, bar_chart().render(), "dotplot1.png")
print('real_end')
