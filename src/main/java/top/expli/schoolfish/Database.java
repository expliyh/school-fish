package top.expli.schoolfish;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作数据库的工具类.
 */
@Service
public class Database {
    @Autowired
    public Database(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    private static EntityManager entityManager;

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

    public static Page<ItemOrder> getItemOrderByPageAsAll(String uid, int page, int per_page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemOrder> query = criteriaBuilder.createQuery(ItemOrder.class);
        Root<ItemOrder> root = query.from(ItemOrder.class);
        Predicate condition = criteriaBuilder.or(
                criteriaBuilder.equal(root.get("sellerID"), uid),
                criteriaBuilder.equal(root.get("buyerID"), uid)
        );

        query.where(condition);


        query.select(root);
        query.orderBy(criteriaBuilder.asc(root.get("createDate")));


        int totalResults = entityManager.createQuery(query).getResultList().size();

        PageRequest pageRequest = PageRequest.of(page - 1, per_page);

        int offset = (page - 1) * per_page;
        List<ItemOrder> orders = entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(per_page)
                .getResultList();
        System.out.println(totalResults);
        return new PageImpl<>(orders, pageRequest, totalResults);
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