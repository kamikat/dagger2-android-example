package moe.banana.dagger2example.ioc;

public interface ActivityComponent<T extends ActivityDelegate> {
    T delegate();
}
