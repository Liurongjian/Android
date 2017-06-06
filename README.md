关于项目
======================
在新达达那段时间，一直想重构公司的项目。可是需求跟得太紧了，一直没有时间。现在离职了，刚好可以趁这段空闲时期，整理在达达时期踩过的坑，学习新的技术。重新调整项目结构，集成当前流行的`retrofit` + `okhttp` + `dagger2` + `mvp` + `rxjava` [基础框架参考]:https://github.com/JessYanCoding/MVPArms

当然，还有我自己搭建的`router`页面路由框架，主要运用了注解与反射。

后续还将有`无痕埋点技术`，`本地日志`，`https + dns + webView` 这些都是我一直想做的，也最能体现我水平的技术项目。

****

router 用法
----------------------
项目大到需要使用多module的方式，考虑使用router来管理页面之间的跳转，可以进一步解耦module之间的依赖。router提供了一座桥梁，让module可以把自己的页面注册到router里，其他module可以在不依赖页面所在的module的情况下跳转到页面来。

#### 页面定义
```java
@Path("/module/a/")
public interface RouterModule {

	@Path("main")
	@Target(AModuleMainActivity.class)
	Intent mainActivity();

	@Path("user")
	@Target(UserActivity.class)
	Intent userActivity(@Query("from") @ExtraType(ExtraType.PUT_STRING) String userFrom,
	                    //当没有@ExtraType注解时会根据参数的类型来自动判断使用bundle.putXXX()来传递参数
	                    @Query("user_id") int userId,
	                    //如果有@NotNull则该参数不能为null
	                    @Query("to") @NotNull String to
	                    );
}
```

#### 注册

```java
router().inject(RouterModule.class);
```
当然demo中使用dagger2来管理router实例，这是一个非常棒的做法，你值得试试。为了进一步解耦主App与module，我们采用了一个取巧的方法，自定义了`ApplicationLifecycleCallbacks`每个module impl一个，inject 放在`ApplicationLifecycleCallbacks`完成。

### 使用
```java
Map<String, Object> params = new ArrayMap<>();
				params.put("from", "南京");
				params.put("user_id", user.getId());
				params.put("to", "上海");

				Intent intent = router.findIntent(getActivity(), "/module/a/user", params);
				startActivity(intent);
```

好了，现在你能愉快地使用router来在多个module之间跳转了，祝你使用愉快。
