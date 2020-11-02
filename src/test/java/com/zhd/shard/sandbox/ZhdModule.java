package com.zhd.shard.sandbox;

public class ZhdModule {
}
/*

@MetaInfServices(Module.class)
@Information(id = "zhd-debug", version = "0.0.1", author = "zhanghongda@kuaishou.com")
public class ZhdModule implements Module {
    private final Logger logger = LoggerFactory.getLogger("DEBUG-SERVLET-ACCESS");

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("update-req")
    public void updateReq(final Map<String, String> param) {
        logger.info("command:update-req param={}", param);
        final String cn = getParameter(param, "class");
        final String mn = getParameter(param, "method");
        if (StringUtils.isBlank(cn) || StringUtils.isBlank(mn)) {
            logger.warn("some param is null, class={}, method={}", cn, mn);
            return;
        }
        //替换
        final String repPath = getParameter(param, "repPath");
        final String repValue = getParameter(param, "repValue");
        new EventWatchBuilder(moduleEventWatcher)
                .onClass(cn)
                .includeSubClasses()
                .onBehavior(mn)
                .onWatch(new AdviceListener() {
                    @Override
                    protected void before(Advice advice) throws Throwable {
                        advice.getBehavior().getParameterTypes();
                        Object[] reqParam = advice.getParameterArray();
                        int index = getReqParamIndex(reqParam, param);
                        if (index < 0) {
                            return;
                        }
                        Object obj = reqParam[index];
                        final String condPath = getParameter(param, "condPath");
                        final String condValue = getParameter(param, "condValue");
                    }
                });
    }

    public int getReqParamIndex(Object[] reqParam, Map<String, String> param) {
        int index = -1;
        final String condIndex = getParameter(param, "condIndex");
        try {
            index = Integer.parseInt(condIndex);
        } catch (Exception e) {
            logger.error("转换异常，condIndex=" + condIndex, e);
        }
        if (index < 0 || reqParam == null || index > reqParam.length - 1) {
            logger.warn("指定的请求参数不存在");
        }
        return index;
    }

    @Command("update-res")
    public void updateRes(final Map<String, String> param) {
        logger.info("command:update-res param={}", param);
        final String cn = getParameter(param, "class");
        final String mn = getParameter(param, "method");
        if (StringUtils.isBlank(cn) || StringUtils.isBlank(mn)) {
            logger.warn("some param is null, class={}, method={}", cn, mn);
            return;
        }
        //条件
        final String condType = getParameter(param, "condType");
        //替换
        final String repPath = getParameter(param, "repPath");
        final String repValue = getParameter(param, "repValue");
        new EventWatchBuilder(moduleEventWatcher)
                .onClass(cn)
                .includeSubClasses()
                .onBehavior(mn)
                .onWatch(new AdviceListener() {
                    @Override
                    protected void afterReturning(Advice advice) throws Throwable {
                        Class<?> returnType = advice.getBehavior().getReturnType();
                        logger.info("returnType={}", returnType);
                        if (void.class.equals(returnType)) {
                            logger.warn("returnType is void, nothing to do");
                            return;
                        }
                        Object returnObj = advice.getReturnObj();
                        logger.info("默认returnObj={}", returnObj);
                        if ("req".equals(condType)) {
                            boolean judge = judgeRequestObj(advice.getParameterArray(), param);
                            logger.info("req judge={}", judge);
                            if (judge) {
                                replaceReturnObj(returnType, returnObj, repPath, repValue);
                            }
                        } else if ("res".equals(condType)) {
                            if (judgeReturnObj(returnObj, param)) {
                                replaceReturnObj(returnType, returnObj, repPath, repValue);
                            }
                        } else {
                            replaceReturnObj(returnType, returnObj, repPath, repValue);
                        }
                    }

                    @Override
                    protected void afterThrowing(Advice advice) throws Throwable {
                        afterReturning(advice);
                    }
                });
    }

    private boolean judgeRequestObj(Object[] reqParam, final Map<String, String> param) {
        //参数条件
        final String condIndex = getParameter(param, "condIndex");
        int index = -1;
        try {
            index = Integer.parseInt(condIndex);
        } catch (Exception e) {
            logger.error("转换异常，condIndex=" + condIndex, e);
        }
        if (index < 0 || reqParam == null || index > reqParam.length - 1) {
            logger.warn("指定的请求参数不存在");
            return false;
        }
        Object obj = reqParam[index];
        final String condPath = getParameter(param, "condPath");
        final String condValue = getParameter(param, "condValue");
        return compare(obj, condPath, condValue);
    }

    private boolean judgeReturnObj(Object obj, final Map<String, String> param) {
        final String condValue = getParameter(param, "condValue");
        final String condPath = getParameter(param, "condPath");
        return compare(obj, condPath, condValue);
    }

    private boolean compare(Object obj, final String condPath, final String condValue) {
        if (obj == null) {
            logger.error("实际的参数为null");
            return false;
        }
        if (condValue == null) {
            logger.warn("比较的参数为null");
            return false;
        }
        if (isPrimitive(obj.getClass())) {
            return condValue.equals(obj.toString());
        }
        if (StringUtils.isBlank(condPath)) {
            logger.warn("非基础类型，比较的路径不存在");
            return false;
        }
        try {
            Object value = JSONPath.read(JSON.toJSONString(obj), condPath);
            if (value == null) {
                logger.error("实际的属性不存在");
                return false;
            }
            if (isPrimitive(value.getClass())) {
                return condValue.equals(value.toString());
            }
            //TODO:比较JSON对象
        } catch (Exception e) {
            logger.error("获取实际的属性异常", e);
        }
        return false;
    }

    private void replaceReturnObj(Class<?> returnType, Object returnObj, String repPath, String repValue)
            throws ProcessControlException {
        Object expect = null;
        logger.info("replaceReturnObj returnType={}, returnObj={}, repPath={}, repValue={}", returnType, returnObj,
                repPath, repValue);
        if (repValue == null) {
            expect = null;
        } else if (isPrimitive(returnType)) {
            expect = repValue;
        } else if (StringUtils.isBlank(repPath)) {
            expect = JSON.parseObject(repValue, returnType);
        } else if (returnObj == null) {
            Object tmp = new JSONObject();
            JSONPath.set(tmp, repPath, repValue);
            String str = JSON.toJSONString(tmp);
            expect = JSON.parseObject(str, returnType);
        } else {
            JSONPath.set(returnObj, repPath, repValue);
            expect = returnObj;
        }
        logger.info("修改returnObj={}", expect);
        ProcessController.returnImmediately(expect);
    }

    private String getParameter(Map<String, String> map, String key) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get(key);
    }

    private List<Pair<String, String>> getParameterLike(Map<String, String> map, String key) {
        List<Pair<String, String>> list = Lists.newArrayList();
        if (map == null || map.isEmpty()) {
            return list;
        }
        for (String s : map.keySet()) {
            if (s.startsWith(key)) {
                list.add(new ImmutablePair<String, String>(s, map.get(s)));
            }
        }
        return list;
    }

    private boolean isPrimitive(Class<?> clazz) {
        if (clazz == null || ClassUtils.isPrimitiveOrWrapper(clazz) || clazz == String.class) {
            return true;
        }
        return false;
    }
}
*/
