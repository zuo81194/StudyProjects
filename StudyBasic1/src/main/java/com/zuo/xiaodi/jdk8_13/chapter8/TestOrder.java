package com.zuo.xiaodi.jdk8_13.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestOrder {
    public static void main(String[] args) {
        //总价 35
        List<VideoOrder> videoOrders1 = Arrays.asList(
                new VideoOrder("20190242812", "springboot教程", 3),
                new VideoOrder("20194350812", "微服务SpringCloud", 5),
                new VideoOrder("20190814232", "Redis教程", 9),
                new VideoOrder("20190523812", "⽹⻚开发教程", 9),
                new VideoOrder("201932324", "百万并发实战Netty", 9));
        //总价 54
        List<VideoOrder> videoOrders2 = Arrays.asList(
                new VideoOrder("2019024285312", "springboot教程", 3),
                new VideoOrder("2019081453232", "Redis教程", 9),
                new VideoOrder("20190522338312", "⽹⻚开发教程", 9),
                new VideoOrder("2019435230812", "Jmeter压⼒测试", 5),
                new VideoOrder("2019323542411", "Git+Jenkins持续集成", 7),
                new VideoOrder("2019323542424", "Idea全套教程", 21));

        //1.统计出同时被两个⼈购买的商品列表(交集)

        //2.统计出两个⼈购买商品的差集
        //3.统计出全部被购买商品的去重并集
        //4.统计两个⼈的分别购买订单的平均价格
        System.out.println("第一笔订单平均价格：" + videoOrders1.stream().collect(Collectors.summarizingInt(VideoOrder::getMoney)).getAverage());
        System.out.println("第二笔订单平均价格：" + videoOrders2.stream().collect(Collectors.summarizingInt(VideoOrder::getMoney)).getAverage());
        //5.统计两个⼈的分别购买订单的总价格
        System.out.println("第一笔订单平均价格：" + videoOrders1.stream().collect(Collectors.summarizingInt(VideoOrder::getMoney)).getSum());
        System.out.println("第二笔订单平均价格：" + videoOrders2.stream().collect(Collectors.summarizingInt(VideoOrder::getMoney)).getSum());
        //
    }
}
