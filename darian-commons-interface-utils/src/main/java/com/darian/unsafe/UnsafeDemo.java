//package com.darian.unsafe;
//
//import jdk.internal.misc.Unsafe;
//
///***
// * JDK 11
// *
// * @author <a href="mailto:1934849492@qq.com">Darian</a>
// * @date 2020/4/1  7:13
// */
//public class UnsafeDemo {
//
//
//    public static void main(String[] args) {
//        Module module = new Module();
//        Module.unsafe.getAndSetInt(module, Module.a_offset, 1);
//        System.out.println(Module.unsafe.getInt(module, Module.a_offset));
//    }
//}
//
//class Module {
//    public static Unsafe unsafe = Unsafe.getUnsafe();
//    private Integer a;
//
//
//    public static long a_offset;
//
//    static {
//        try {
//            a_offset = unsafe.objectFieldOffset(Module.class, "a");
//        } catch (InternalError e) {
//            System.err.println("属性值找不到：" + e.getLocalizedMessage());
//        }
//
//    }
//}


//         11  11 11 11
//           <plugin>
//                <groupId>org.apache.maven.plugins</groupId>
//                <artifactId>maven-compiler-plugin</artifactId>
//                <version>${maven-compiler-plugin-version}</version>
//                <configuration>
//                    <compilerArgs>
//                        <arg>--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED</arg>
//                    </compilerArgs>
//                    <source>${maven.compiler.source}</source>
//                    <target>${maven.compiler.target}</target>
//                    <release>8</release>
//                </configuration>
//            </plugin>
