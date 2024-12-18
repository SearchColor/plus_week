package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    @DisplayName("item entity test")
    public void create() {


        User ownerUser = User.builder().email("aa@aa.com").role("user").build();
        User manageUser = User.builder().email("bb@bb.com").role("admin").build();

      Item item = new Item("testItem" ,"test",manageUser,ownerUser);
      itemRepository.save(item);

      System.out.println("item status : "+ item.getStatus());
      System.out.println("item name : "+ item.getName());

    }

}