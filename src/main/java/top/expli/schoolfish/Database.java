package top.expli.schoolfish;

import org.springframework.data.jpa.repository.JpaRepository;

public class Database {
    public Database() {

    }

    private static ItemOrderRepository itemOrderRepository;

    public static String addItemOrder(ItemOrder order) {
        ItemOrder savedOrder = itemOrderRepository.save(order);
        return savedOrder.getOrderID();
    }
}

interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}