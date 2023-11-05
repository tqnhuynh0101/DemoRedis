package com.example.demoredis.respository;

import com.example.demoredis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate template;

    public Product save(Product product){
        try{
            template.opsForHash().put(HASH_KEY,product.getId(),product);
            return product;
        }
       catch (Exception e){
            return null;
       }
    }

    public List<Product> findAll(){
        try {
            return template.opsForHash().values(HASH_KEY);
        }
        catch (Exception e){
            return null;
        }
    }

    public Product findProductById(int id){
        try{
        return (Product) template.opsForHash().get(HASH_KEY,id);
        }
        catch (Exception e){
            return null;
        }
    }


    public String deleteProduct(int id){
        try{
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
        }
        catch (Exception e){
            return null;
        }
    }
}
