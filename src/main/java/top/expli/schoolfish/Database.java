package top.expli.schoolfish;

import org.springframework.data.jpa.repository.JpaRepository;

public class Database {
    public Database(){

    }
}

interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}