package top.expli.schoolfish;

import org.springframework.data.jpa.repository.JpaRepository;

public class Database {
}

interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}