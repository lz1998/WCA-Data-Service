# WCA-Data-Service
运行就能用  
首次启动手动调用接口更新wca数据，以后每天中午12:15自动更新数据  
Windows可通过右下角托盘退出程序
## 运行参数：
- server.port 端口号默认80
- wca.path wca相关数据保存路径(linux和Windows不一样，默认~/wca)
- wca.export-zip wca导出zip的名字
- wca.url wca导出zip的下载地址
- wca.extract-path wca导出zip的解压目录


## 接口
- 有多个返回结果的接口，可以加参数pageNum(default=0),pageSize(default=10,max=1000)
### 根据关键词搜人(WCA ID或名字中出现)
http://wcads.lz1998.xin/wcaPerson/searchPeople?q=李政 2016

### 根据WCA ID精确找人
http://wcads.lz1998.xin/wcaPerson/findPersonById?id=2016LIZH03

### 根据WCA ID找所有项目单次最好成绩
http://wcads.lz1998.xin/wcaSingle/findBestResultsByPersonId?personId=2016LIZH03

### 根据WCA ID和项目ID找项目单次最好成绩
http://wcads.lz1998.xin/wcaSingle/findBestResultByPersonIdAndEventId?personId=2016LIZH03&eventId=333

### 根据WCA ID找所有项目平均最好成绩
http://wcads.lz1998.xin/wcaAverage/findBestResultsByPersonId?personId=2016LIZH03

### 根据WCA ID和项目ID找项目平均最好成绩
http://wcads.lz1998.xin/wcaAverage/findBestResultByPersonIdAndEventId?personId=2016LIZH03&eventId=333

### 根据WCA ID和项目ID，找所有参赛经历
http://wcads.lz1998.xin/wcaResult/findResultsByPersonIdAndEventId?personId=2016LIZH03&eventId=333