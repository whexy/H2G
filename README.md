## ConfigLoader
ConfigLoader基于Json库的辅助程序，简化了读取操作

### API Reference
1. ConfigLoader(String fileName) 构造器
2. get...(String path) 读取指定位置的值

### Syntax of path
| 符号 | 含义 | 适用类 |
| --- | --- | --- |
| . | 分隔符 | 无 |
| Digit | List的下标 | JsonArray |
| Token | Map的Key | JsonObject |

#### Example
ConfigLoader cL = new ConfigLoader("facebook.json");  
String str = cL.getStr("data.1.message");  

### Update Log
#### Version 1 rev.A (2018.11.28)

## SigDraw
SigDraw是基于StdDraw魔改的产物

### API Reference
##### Extra API
1. SigDraw() 构造器
2. enableAntialiasing() 启动反锯齿(默认开启)
3. disableAntialiasing() 关闭反锯齿
4. getBuffImg() 获取内存中的图片(BufferedImage类型)
5. getScaledImage() 获取缩放后的图片(默认情况下，高分辨率模式缩小一半，普通模式不缩放)(BufferedImage类型)
6. loadImage() 从本地文件载入图片(基于getImage(),同时废除该函数)
7. setBuffImg() 重设内存缓存的图片
8. useHighResolution() 切换高分辨率模式
9. setScaleUnaltered() 离散化坐标系

##### Modified API
1. picture() 修改为只能载入BufferedImage图像
2. init() 修改启动流程，优化嵌套渲染的情况
3. setCanvasSize() 修改执行流程

#### Note
在调用构造函数时启用高分辨率模式，且指定空画布尺寸，自动设置画布尺寸为输入尺寸大小的4倍。  
但在调用构造函数时启用高分辨率模式，且传入初始图像，不会改变画布尺寸（仍等于图像尺寸）。  
直接调用useHighResolution()函数不会改变已有画布尺寸大小。  

### Update Log

#### Version 1 rev.B+ (2018.11.29)

1. 删除旧版本冗余JavaDoc，并新增了一些（冗余的JavaDoc）重要使用提示
2. 在  更  多  代  码  之  间  加  了  空  格
3. 调整了一些函数及参量的名称以符合使用者习惯

#### Version 1 rev.B (2018.11.27)
1. 添加四倍分辨率绘制支持API
2. 添加离散化坐标API
3. 修复BUG

#### Version 1 rev.A (2018.11.23)
1. 修改为动态类型，允许实例化
2. 剥离Event,Swing,双缓冲部分
3. 修改部分函数和启动流程，添加基础函数

### Peformance Caution
单线程缩放速度参考(Core i7 3630QM)  

（AA: 反锯齿 HR: 2k分辨率缩放）  

| 模式 | 帧率(FPS) |
| --- | --- |
| AA_HR_UQ | 1.488316713796696 |
| AA_HR_Q | 10.463899546564353 |
| AA_HR_AUTO | 23.059185242121448 |
| AA_HR_SPD | 37.59398496240601 |
| AA_HR_BL | 23.752969121140143 |
| AA_UQ | 5.512679162072767 |
| AA_Q | 25.40220152413209 |
| AA_AUTO | 24.97918401332223 |
| AA_SPD | 44.11764705882353 |
| AA_BL | 37.40648379052369 |

单线程大压力缩放速度参考2(Core i7 8750H 节电模式) 

| 模式               | 帧率(FPS)          |
| ------------------ | ------------------ |
| AA_HR_UQ | 0.7746533426291735 |
|AA_HR_Q|4.365541327124563|
|AA_HR_AUTO| 26.619343389529725|
|AA_HR_SPD |29.615004935834158|
|AA_HR_BL| 13.071895424836601|
|AA_UQ| 2.1216407355021216|
|AA_Q| 13.507429085997298|
|AA_AUTO| 37.08281829419035|
|AA_SPD| 43.10344827586207|
|AA_BL |33.63228699551569 |