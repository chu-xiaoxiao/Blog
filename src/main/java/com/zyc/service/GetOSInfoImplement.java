package com.zyc.service;

import net.sf.json.JSONObject;

import java.lang.management.*;
import java.util.List;

/**
 * 获取操作信息和jvm信息的服务实现类
 * Created by YuChen Zhang on 17/09/27.
 */
public class GetOSInfoImplement implements GetOSInfo{
    /**
     * 获取JVM参数
     * @return
     */
    @Override
    public JSONObject getJVM() {
        JSONObject jsonObject = new JSONObject();
        //虚拟机信息
        jsonObject.put("JVMInfo",GetOSInfoImplement.showJvmInfo());
        //操作系统信息
        jsonObject.put("OSInfo",GetOSInfoImplement.showSystem());
        //类加载器信息
        jsonObject.put("ClassLoadingInfo",GetOSInfoImplement.showClassLoading());
        //内存信息
        jsonObject.put("MemoryManagerInfo",GetOSInfoImplement.showMemoryManager());
        //JVM内存使用信息
        jsonObject.put("JvmMemoryInfo",GetOSInfoImplement.showMemoryInfo());
        return jsonObject;
    }

    /**
     * 获取OS参数
     * @return
     */
    @Override
    public JSONObject getOS() {
        return null;
    }
    /**
     * Java 虚拟机的内存系统
     */
    public static JSONObject showJvmInfo(){
        JSONObject jsonObject = new JSONObject();
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor = mxbean.getVmVendor();
        jsonObject.put("jvm_name",mxbean.getVmName());
        jsonObject.put("jvm_version" , mxbean.getVmVersion());
        jsonObject.put("jvm_bootClassPath" , mxbean.getBootClassPath());
        jsonObject.put("jvm_start_time" , mxbean.getStartTime());
        return jsonObject;
    }
    /**
     * Java 虚拟机的内存系统
     */
    public static JSONObject showMemoryInfo() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = mem.getHeapMemoryUsage();
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("Heap_committed",heap.getCommitted());
        jsonObject.put("init",heap.getInit());
        jsonObject.put("max",heap.getMax());
        jsonObject.put("used",heap.getUsed());
        return jsonObject;

    }
    /**
     * Java 虚拟机在其上运行的操作系统
     */
    public static JSONObject showSystem() {
        JSONObject jsonObject = new JSONObject();
        OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
        jsonObject.put("Architecture " , op.getArch());
        jsonObject.put("Processors " , op.getAvailableProcessors());
        jsonObject.put("System_name " , op.getName());
        jsonObject.put("System_version " , op.getVersion());
        jsonObject.put("Last_minuteload " , op.getSystemLoadAverage());
        return jsonObject;
    }

    /**
     * Java 虚拟机的类加载系统
     */
    public static JSONObject showClassLoading(){
        JSONObject jsonObject = new JSONObject();
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        jsonObject.put("TotalLoadedClassCount " , cl.getTotalLoadedClassCount());
        jsonObject.put("LoadedClassCount" , cl.getLoadedClassCount());
        jsonObject.put("UnloadedClassCount" , cl.getUnloadedClassCount());
        return jsonObject;
    }

    /**
     * Java 虚拟机的编译系统
     */
    public static JSONObject showCompilation(){
        JSONObject jsonObject = new JSONObject();
        CompilationMXBean com = ManagementFactory.getCompilationMXBean();
        jsonObject.put("TotalCompilationTime" , com.getTotalCompilationTime());
        jsonObject.put("name" , com.getName());
        return jsonObject;
    }

    /**
     * Java 虚拟机的线程系统
     */
    public static JSONObject showThread(){
        JSONObject jsonObject = new JSONObject();
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        jsonObject.put("ThreadCount" , thread.getThreadCount());
        jsonObject.put("AllThreadIds" , thread.getAllThreadIds());
        jsonObject.put("CurrentThreadUserTime" , thread.getCurrentThreadUserTime());
        //......还有其他很多信息
        return jsonObject;
    }

    /**
     * Java 虚拟机中的垃圾回收器。
     */
    public static JSONObject showGarbageCollector(){
        JSONObject jsonObject = new JSONObject();
        List<GarbageCollectorMXBean> gc = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean GarbageCollectorMXBean : gc){
            jsonObject.put("name" , GarbageCollectorMXBean.getName());
            jsonObject.put("CollectionCount" , GarbageCollectorMXBean.getCollectionCount());
            jsonObject.put("CollectionTime" , GarbageCollectorMXBean.getCollectionTime());
        }
        return jsonObject;
    }

    /**
     * Java 虚拟机中的内存管理器
     */
    public static JSONObject showMemoryManager(){
        JSONObject jsonObject = new JSONObject();
        List<MemoryManagerMXBean> mm = ManagementFactory.getMemoryManagerMXBeans();
        for(MemoryManagerMXBean eachmm : mm){
            jsonObject.put("name" , eachmm.getName());
            jsonObject.put("MemoryPoolNames" , eachmm.getMemoryPoolNames().toString());
        }
        return jsonObject;
    }

    /**
     * Java 虚拟机中的内存池
     */
    public static JSONObject showMemoryPool(){
        JSONObject jsonObject = new JSONObject();
        List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean mp : mps){
            jsonObject.put("name" , mp.getName());
            jsonObject.put("CollectionUsage" , mp.getCollectionUsage());
            jsonObject.put("type" , mp.getType());
        }
        return jsonObject;
    }
}
