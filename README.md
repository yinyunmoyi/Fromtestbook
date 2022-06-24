# Fromtestbook

![QQ图片20220623230134](QQ图片20220623230134.png)

MyBatis 与 Hibernate 的对比：

|   对比指标   |        Hibernate        |                 MyBatis                  |
| :------: | :---------------------: | :--------------------------------------: |
|    类型    |   半自动，半ORM （允许用 Map ）   |             全自动 ORM （只能用实体类）             |
|    中心    | SQL（一切 Mapper 里都是 SQL ） |            对象关系（一切操作都围绕对象关系）             |
|  SQL优化   |     容易（直接修改 SQL 即可）     | 困难（修改 SQL 后还需要转换为 HQL ，如果直接上 SQL 则会破坏封装） |
|   缓存机制   |     一般（管理不慎会出现脏数据）      |      完善（基于 ORM 的对象管理机制，出现脏数据会给出提示）       |
| 数据库可移植性  |   不好（切换数据库需要重新写 SQL ）   |         好（可配置数据库类型自动切换生成的 SQL ）          |
|  日志打印体系  |    很有限（只能打印基本日志及交互）     |    完善（ SQL 记录、关系异常、优化警告、缓存提示、脏数据警告等）     |
| 开发效率、工作量 |       效率相对低，工作量多        |               开发效率相对高，工作量少               |
|   学习成本   |     较低（需要学习的内容相对少）      |      高（需要额外学习 JPA 、HQL 、QBC 、抓取策略等）      |
|   适用场景   |    比较容易出现复杂 SQL 的项目     |                复杂查询很少的项目                 |

