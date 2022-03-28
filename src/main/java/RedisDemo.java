import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis study
 *
 */
public class RedisDemo {

    /**
     * hset
     */
    @Test
    public void test03(){
       Jedis jedis = new Jedis();
       jedis.hset("user","name","lisi");
       jedis.hset("user","age","23");
       jedis.hset("user","gender","male");

        String name =  jedis.hget("user","name");
        System.out.println(name);
        Map<String,String> user  = jedis.hgetAll("user");
        Set<String> keySets = user.keySet();
        for(String key :keySets){
            System.out.println("key = "+key+"::value="+user.get(key));
        }

        jedis.close();
   }

    /**
     * list test
     */
   @Test
   public void test04(){
       Jedis jedis = new Jedis();
       jedis.lpush("mylist","a","b","c");
       jedis.rpush("mylist","a","b","c");
       List<String> myList = jedis.lrange("mylist",0,-1);
       System.out.println(myList);
       jedis.close();

   }

    /**
     * set
     */
   @Test
   public void test05(){
       Jedis jedis = new Jedis();
       jedis.sadd("myset","java","php","c++");
       Set<String> setList = jedis.smembers("myset");
       System.out.println(setList);

   }

    /**
     * SortSet
     */
   @Test
   public void test06(){
       Jedis jedis = new Jedis();
       jedis.zadd("mysortedset",3,"亚瑟");
       jedis.zadd("mysortedset",30,"后裔");
       jedis.zadd("mysortedset",55,"孙悟空");
       Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
       System.out.println(mysortedset);

   }

    /**
     * jedisPool
     * jedisPool 连接池对象
     */
   @Test
   public  void test07(){


       JedisPoolConfig config = new JedisPoolConfig();
       config.setMaxTotal(50);
       config.setMaxIdle(10);
       // 创建 jedis 连接池对象
       JedisPool jedisPool = new JedisPool(config,"localhost",6379);

       // 获取连接
       Jedis jedis = jedisPool.getResource();
       //使用
       jedis.set("hehe","haha");

       //关闭 归还到连接池中

       jedis.close();

   }

    /**
     * 连接池工具类获取
     */
   @Test
   public void test08(){
       Jedis jedis = JedisPoolUtils.getJedis();
       jedis.set("hello","heihei");
       jedis.close();


   }
}
