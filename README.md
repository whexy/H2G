## SigDraw
SigDraw是基于StdDraw魔改的产物

### Version 1 rev.A (2018.11.23)
#### Feature
1. 修改为动态类型，允许实例化
2. 剥离Event,Swing,双缓冲部分
#### API Change

##### Add
1. SigDraw() 构造器
2. enableAntialiasing() 启动反锯齿(默认开启)
3. disableAntialiasing() 关闭反锯齿
4. getBuffImg() 返回图片的内存缓存(BufferedImage类型)
5. loadImage() 载入图片文件(基于getImage(),同时废除该函数)
6. setBuffImg() 重设内存缓存的图片

##### Modify
1. picture() 修改为只能载入BufferedImage图像
2. init() 修改启动流程，优化嵌套渲染的情况
