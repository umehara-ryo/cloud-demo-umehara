package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;


    public Order queryOrderById(Long orderId) {
        // 1.注文を検索
        Order order = orderMapper.findById(orderId);

        //2.feignでレモートコール
        User user = userClient.findById(order.getUserId());

        //3.userをorderに代入
        //卒業までずっとご指導いただけると思っていたのですが、転任されると知り寂しく思っています。
        //どうかいつまでもお元気で、お幸せでありますように心からお祈りしております。
        //一年間、本当にありがとうございました。
        order.setUser(user);

        //4.返す
        return order;
    }


    //    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.注文を検索
//        Order order = orderMapper.findById(orderId);
//
//        //2.RestTemplateを利用し、httpリクエスト請求を発送する
//        String url = "http://userservice/user/" + order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//
//        //3.userをorderに代入
//        order.setUser(user);
//
//        //4.返す
//        return order;
//    }
}
