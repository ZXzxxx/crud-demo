package com.zx.cruddemo.util;

/**
 * Created by dell on 2018/1/17.
 */
public class Common {

    //dao类定义一个MyRepository的接口，可以写一个自定义的方法
    //自定义dao接口
    public static final String ENTITY_DAO_PARENT = "public interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {\n\n" +
            "//里面写一些自定义的方法\n}";
    //自定义接口的实现类
    public static final String ENTITY_DAO_PARENT_IMPL = "/**\n" +
            "     * 继承SimpleJpaRepository，SimpleJpaRepository帮助实现了JpaRepository中的方法。否则就要重新实现一遍\n" +
            "     * 实现类，写在MyRepository接口中自定义的方法的具体实现\n" +
            "     */\n" +
            "    public class MyRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>\n" +
            "            implements MyRepository<T, ID>{\n" +
            "\n" +
            "        private final EntityManager entityManager;\n" +
            "\n" +
            "        //父类没有不带参数的构造方法，这里手动构造父类\n" +
            "        public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {\n" +
            "            super(domainClass, entityManager);\n" +
            "            this.entityManager = entityManager;\n" +
            "        }\n" +
            "\n" +
            "        //写自定义方法的具体实现。。。\n" +
            "    }";
    //自定义工厂
    public static final String ENTITY_DAO_PARENT_FACTORY = "/**创建一个自定义工厂，在这个工厂里注册自定义的MyRepositoryImpl的实现*/\n" +
            "public class MyRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {\n" +
            "    public MyRepositoryFactoryBean(Class<? extends R> repositoryInterface) {\n" +
            "    super(repositoryInterface);\n" +
            "    }\n" +
            "    @Override\n" +
            "    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {\n" +
            "        return new MyRepositoryFactory(em);\n" +
            "    }\n" +
            "\n" +
            "    //创建一个内部类，该类不用在外部访问\n" +
            "    private static class MyRepositoryFactory<T, I extends Serializable>\n" +
            "            extends JpaRepositoryFactory {\n" +
            "\n" +
            "        private final EntityManager em;\n" +
            "\n" +
            "        public MyRepositoryFactory(EntityManager em) {\n" +
            "            super(em);\n" +
            "            this.em = em;\n" +
            "        }\n" +
            "\n" +
            "        //设置具体的实现类是BaseRepositoryImpl\n" +
            "        @Override\n" +
            "        protected Object getTargetRepository(RepositoryInformation information) {\n" +
            "            return new MyRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em);\n" +
            "        }\n" +
            "\n" +
            "        //设置具体的实现类的class\n" +
            "        @Override\n" +
            "        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {\n" +
            "            return MyRepositoryImpl.class;\n" +
            "        }\n" +
            "    }\n" +
            "}";


    //dao类的数据主体
    public static final String ENTITY_DAO = "public interface "
            + "#%ENTITY_CLASS#Dao extends MyRepository<#%ENTITY_CLASS#, Integer> {\n\n}";

    //然后抽象出来一个basicService
    //basicService的数据主体
    public static final String ENTITY_BASIC_SERVICE = "public abstract class BasicService<T extends Serializable,ID extends Serializable> {\n\n\n" +
            "@Autowired\n" +
            "MyRepository<T,ID> basicDao;\n" +
            "\n" +
            "/**\n" +
            " * https://www.cnblogs.com/rulian/p/6533109.html\n" +
            " */\n" +
            "//传入的参数是要获取的实体类，返回结果可有可无。查找单个实体类的时候用这个传条件\n" +
            "//通过ID查找也用这个,用getOne时返回的是一个代理对象，如果查找的ID为空就会抛异常\n" +
            "public Optional<T> findOne(Example<T> t){\n" +
            "    return basicDao.findOne(t);\n" +
            "}\n" +
            "\n" +
            "//单个动态条件查找，查询到某个实体类信息\n" +
            "public Optional<T> findOneByX(Specification<T> spec){\n" +
            "    return basicDao.findOne(spec);\n" +
            "}\n" +
            "\n" +
            "//得到某实体类所有信息\n" +
            "public List<T> findAllT() {\n" +
            "    return basicDao.findAll();\n" +
            "}\n" +
            "\n" +
            "//多个动态条件查找，查询到满足条件的多个实体类信息\n" +
            "public List<T> findTsByXX(Specification<T> specification) {\n" +
            "    return basicDao.findAll(specification);\n" +
            "}\n" +
            "\n" +
            "public Page<T> findAllT(Pageable pageable) {\n" +
            "    return this.basicDao.findAll(pageable);\n" +
            "}\n" +
            "\n" +
            "//单个增加\n" +
            "public void add(T t) {\n" +
            "    this.basicDao.save(t);\n" +
            "}\n" +
            "\n" +
            "//批量增加\n" +
            "public void addList(Iterable<T> ts) {\n" +
            "    this.basicDao.saveAll(ts);\n" +
            "}\n" +
            "\n" +
            "//单个更新\n" +
            "public void update(T t) {\n" +
            "    this.basicDao.saveAndFlush(t);\n" +
            "}\n" +
            "\n" +
            "//单个删除，传的是实体类\n" +
            "public void delete(T t) {\n" +
            "    this.basicDao.delete(t);\n" +
            "}\n" +
            "\n" +
            "//批量删除,后台是生成一条SQL语句[之前那个是一条条删除]，效率更高些\n" +
            "public void deleteList(Iterable<T> ts) {\n" +
            "    this.basicDao.deleteInBatch(ts);\n" +
            "}\n\n\n}";



    //service的数据主体
    public static final String ENTITY_SERVICE = "public class #%ENTITY_CLASS#Service extends BasicService<#%ENTITY_CLASS#, Integer> {\n\n}";

    //baseController的数据主体
    public static final String BASE_CONTROLLER = "public abstract class BaseController {\n\n\n";
}
