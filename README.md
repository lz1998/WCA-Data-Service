# WCA-Data-Service
运行就能用  
首次启动手动调用接口更新wca数据，以后每天中午12:15自动更新数据  
## 参数：
- wca.path wca相关数据保存路径(linux和Windows不一样，默认~/wca)
- wca.export-zip wca导出zip的名字
- wca.url wca导出zip的下载地址
- wca.extract-path wca导出zip的解压目录
- server.port 端口号

## 接口
- /wca/downloadData  
下载wca数据
- /wca/extractData  
解压wca数据
- /wca/importData  
导入wca数据
- /wca/updateData  
下载、解压、导入wca数据
- /wcaPerson/searchPeople?q=李政 2016  
模糊查询，空格分割，id或名字中出现每个词
- /wcaPerson/findPersonById?id=2016LIZH03  
通过id精确查找
- /wcaSingle/findBestResultsByPersonId?personId=2016LIZH03  
通过id查找所有项目单次最快成绩和排名
- /wcaAverage/findBestResultsByPersonId?personId=2016LIZH03  
通过id查找所有项目平均最快成绩和排名
