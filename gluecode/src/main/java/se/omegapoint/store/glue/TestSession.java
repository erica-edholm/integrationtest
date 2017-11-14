package se.omegapoint.store.glue;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class TestSession {

    Map<Class<?>, ? super Object> cacheByClass = new HashMap<>();
    Map<String, ? super Object> cacheByString = new HashMap<>();

    public <T> T get(Class<T> key){
        T value = (T) cacheByClass.get(key);
        if(value == null){
            throw new NoSuchElementException(String.format("No value matching key '%s'", key.toString()));
        }
        return value;
    }

    public <T> T get(String key){
        T value =  (T) cacheByString.get(key);
        if(value == null){
            throw new NoSuchElementException(String.format("No value matching key '%s", key));
        }
        return value;
    }

    public <T> void put(Class<T>key, T value){
        cacheByClass.put(key, value);
    }

    public <T> void put(String key, T value){
        cacheByString.put(key, value);
    }

    public void clear(){
        cacheByClass.clear();
        cacheByString.clear();
    }
}
