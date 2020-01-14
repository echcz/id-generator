# id-generator

分布式ID生成器

使用类`Snowflake`算法，生成一个全局唯一、趋时递增的正long型ID。结构如下:

| 时间 | 序列 | 业务 | 机器 | 总计 |
|:---:|:---:|:----:|:---:|:---:|
| 40  |  8  |  9   |  6  | 63  |

特性:

* 可使用 34 年
* 支持单机 256/ms 并发量
* 支持 512 条业务线
* 支持 64 台机器
