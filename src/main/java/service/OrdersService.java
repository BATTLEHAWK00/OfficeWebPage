package service;

import bean.Order;

import java.util.List;

public interface OrdersService {
    /**
     * �ύ�û�����
     *
     * @param order
     */
    void doPostOrder(Order order);

    /**
     * ��ȡָ���û��Ĺ���
     *
     * @param uid �û�UID
     * @return
     */
    List<Order> doGetOrdersByUID(String uid);
}
