package top.expli.schoolfish;

import org.springframework.data.jpa.repository.JpaRepository;
import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作数据库的工具类.
 */
public class Database {
    public Database() {

    }

    private static ItemOrderRepository itemOrderRepository;

    /**
     * 向数据库中添加物品订单.
     *
     * @param order 物品订单的实体 (Entity)
     * @return 字符串形式的订单号，最后的 @ 之后的数字代表订单类型
     */
    public static String addItemOrder(ItemOrder order) {
        ItemOrder savedOrder = itemOrderRepository.save(order);
        return savedOrder.getOrderID();
    }

    /**
     * 更新数据库中的物品订单.
     *
     * @param order 物品订单实体
     * @return 字符串形式的订单号
     */
    public static String updateItemOrder(ItemOrder order) {
        ItemOrder savedOrder = itemOrderRepository.save(order);
        return savedOrder.getOrderID();
    }

    /**
     * 更新数据库中的订单（自适应类型）
     *
     * @param order 订单实体
     * @return 字符串形式的订单号
     */
    public static String updateOrder(Order order) {
        if (order instanceof ItemOrder) {
            return updateItemOrder((ItemOrder) order);
        }
        return "";
    }

    public static Order getOrder(String orderID) throws IDFormatInvalid, OrderNotFound {
        String pattern = "(.+)@(.+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(orderID);
        Order order = null;
        long rawOrderID = -1;
        int orderType = -1;
        if (m.find()) {
            try {
                rawOrderID = Long.parseLong(m.group(1));
                orderType = Integer.parseInt(m.group(2));
            } catch (NumberFormatException numberFormatException) {
                throw new IDFormatInvalid("无效的订单号");
            }
        }
        switch (orderType) {
            case OrderTypes.ITEM_ORDER -> {
                order = itemOrderRepository.getReferenceById(rawOrderID);
            }
        }
        if (order == null) {
            throw new OrderNotFound();
        }
        return order;
    }
}

interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}