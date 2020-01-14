package cn.echcz;

import java.util.Date;

/**
 * 分布式ID生成器
 */
public class IdGenerator {
    /**
     * 业务标识左移位数
     * = 机器标识占用位数
     */
    private static final int businessLs = 6;
    /**
     * 序列标识左移位数
     * = 机器标识占用位数 + 业务标识占用位数
     */
    private static final int sequenceLs = 15;
    /**
     * 时间标识左移位数
     * = 机器标识占用位数 + 业务标识占用位数 + 序列标识占用位数
     */
    private static final int timeLs = 23;

    private long startTime;
    private long macNo;
    private long sequence = 0;
    private long lastTime = -1;

    /**
     * @param startTime 起始时间的毫秒表示
     * @param macNo 机器标识
     */
    public IdGenerator(long startTime, long macNo) {
        this.startTime = startTime;
        this.macNo = macNo;
    }

    /**
     * @param startTime 起始时间
     * @param macNo 机器标识
     */
    public IdGenerator(Date startTime, long macNo) {
        this(startTime.getTime(), macNo);
    }

    /**
     * 使用类`Snowflake`算法，生成一个全局唯一、趋时递增的正long型ID
     * 结构为: 时间标识40bit | 序列标识8bit | 业务标识9bit | 机器标识6bit
     * 如果各标识长度超限，不会报错，但会生成错误的ID
     *
     * @param businessId 业务标识
     * @return ID
     */
    public long generate(long businessId) {
        return getTimeSequence() | (businessId << businessLs) | macNo;
    }

    private synchronized long getTimeSequence() {
        long now = System.currentTimeMillis() - startTime;
        if (now == lastTime) {
            sequence++;
        } else {
            lastTime = now;
            sequence = 0;
        }
        return (now << timeLs) | (sequence << sequenceLs);
    }
}
