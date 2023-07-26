/**
 * My simple realization
 *
 * @author chubatiy
 * @param <T> type of value
 */
public class MyCustomCache<T> implements Cache<T> {

    //holder
    private final Map<String, T> holder;
    //key list
    private final List<String> keys;
    //max size of cached values
    private int maxSize;

    /**
     * Constructor
     */
    public MyCustomCache() {
        this.holder = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, T value) {
        //if holder contains a key
        if (this.holder.containsKey(key)) {
            //add new value
            this.holder.put(key, value);
            return;
        }
        //check if i need to remove
        if (getCurrentCacheSize() >= getMaxSize()) {
            removeOldest(1);
        }
        //add new value
        this.holder.put(key, value);
        //remove key
        this.keys.add(key);
    }

    /**
     * Remove oldest keys
     *
     * @param count count to remove
     */
    private void removeOldest(int count) {
        List<String> keysToRemove = this.keys.subList(0, count);
        this.holder.keySet().removeAll(keysToRemove);
        this.keys.removeAll(keysToRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(String key) {
        return this.holder.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxSize(int maxSize) {
        //check value
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be greater then 0");
        }
        //if maxSize less than holder size
        if (this.holder.size() > maxSize) {
            removeOldest(maxSize);
        }
        //remember
        this.maxSize = maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentCacheSize() {
        return this.holder.size();
    }

}
