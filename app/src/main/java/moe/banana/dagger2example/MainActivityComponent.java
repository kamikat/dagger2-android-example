package moe.banana.dagger2example;

import dagger.Component;
import moe.banana.dagger2example.ioc.ActivityComponent;

@Component(modules = MainActivity.class)
public interface MainActivityComponent extends ActivityComponent<MainActivityDelegate> {
    MainActivityDelegate delegate();
}
