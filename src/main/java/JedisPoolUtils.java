import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedisPool 工具类
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;


    static {
        //读取配置文件
        InputStream is   = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        //获取数据

        JedisPoolConfig config  = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        jedisPool =  new JedisPool(config,pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }
    /**
     * 获取连接的方法
     */

    public static Jedis getJedis(){
        return  jedisPool.getResource();
    }
}
