# WCA-Data-Service
运行就能用  
首次启动先访问/wca/updateData，以后每天中午12:15自动更新数据  
## 参数：
- wca.path wca相关数据保存路径(linux和Windows不一样，默认~/wca)
- wca.export-zip wca导出zip的名字
- wca.url wca导出zip的下载地址
- wca.extract-path wca导出zip的解压目录
- server.port 端口号

## 接口
- /wcaPerson/findWcaPersonById?id=2016LIZH03
- /wcaPerson/findWcaPeopleByNameContaining?name=李政
- /wcaRankAverage/findWcaRankAveragesByPersonId?personId=2016LIZH03
- /wcaRankSingle/findWcaRankSinglesByPersonId?personId=2016LIZH03
