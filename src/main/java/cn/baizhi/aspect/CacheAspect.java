package cn.baizhi.aspect;

import cn.baizhi.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
    @Autowired
    private UserService us;

    @Autowired
    private RedisTemplate redisTemplate;

        @Around("execution(* cn.baizhi.service.*Impl.query*(..))")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            HashOperations opsForHash = redisTemplate.opsForHash();
            redisTemplate.setKeySerializer(new StringRedisSerializer()); //设置key不序列化
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());//取消小key的序列化
            System.out.println("进入环绕通知");
            Object proceed = null;

            StringBuilder sb= new StringBuilder();
            //类的全路径
            String className = joinPoint.getTarget().getClass().getName();
            sb.append(className);
            //方法名
            String methodName = joinPoint.getSignature().getName();
            sb.append(methodName);
            //实参名
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {

                sb.append(arg);
            }

            if(opsForHash.hasKey(className,sb.toString())){
                proceed = opsForHash.get(className,sb.toString());
            }else {
                proceed = joinPoint.proceed();
                opsForHash.put(className,sb.toString(),proceed);
            }
            return proceed;//返回目标方法返回值
        }

    @After("@annotation(cn.baizhi.annotation.DeleteCache)")
    public void after(JoinPoint joinPoint){  //增删改成功后 删除缓存
        System.out.println("后置通知");
        String name = joinPoint.getTarget().getClass().getName();//目标对象
        redisTemplate.delete(name);
//
//        Set keys = redisTemplate.keys("*");
//        for (Object key : keys) {
//            String newKey = (String)key;
//            if(newKey.startsWith(name)){
//                redisTemplate.delete(key);
//            }
//        }
//        joinPoint.getSignature();//方法签名
//        joinPoint.getArgs();//方法参数
    }

}
