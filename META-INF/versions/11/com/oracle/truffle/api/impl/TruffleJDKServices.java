package com.oracle.truffle.api.impl;

import java.util.*;

final class TruffleJDKServices
{
    TruffleJDKServices() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: fstore_1       
        //     3: lsub           
        //     4: dadd           
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void exportTo(final ClassLoader loader, final String moduleName) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: laload         
        //     2: idiv           
        //     3: ladd           
        //     4: fdiv           
        //     5: dsub           
        //     6: laload         
        //     7: frem           
        //     8: lsub           
        //     9: fsub           
        //    10: idiv           
        //    11: lsub           
        //    12: dadd           
        //    13: ineg           
        //    14: laload         
        //    15: astore_2       
        //    16: lsub           
        //    17: ineg           
        //    18: imul           
        //    19: ddiv           
        //    20: isub           
        //    21: istore_0        /* loader */
        //    22: aconst_null    
        //    23: nop            
        //    24: dload           106
        //    26: ladd           
        //    27: fneg           
        //    28: ladd           
        //    29: laload         
        //    30: idiv           
        //    31: ladd           
        //    32: fdiv           
        //    33: dsub           
        //    34: laload         
        //    35: frem           
        //    36: lsub           
        //    37: fsub           
        //    38: idiv           
        //    39: lsub           
        //    40: dadd           
        //    41: ineg           
        //    42: laload         
        //    43: astore_2        /* truffleModule */
        //    44: lsub           
        //    45: ineg           
        //    46: imul           
        //    47: ddiv           
        //    48: isub           
        //    49: aconst_null    
        //    50: nop            
        //    51: iconst_3       
        //    52: lmul           
        //    53: fdiv           
        //    54: fneg           
        //    55: ddiv           
        //    56: dmul           
        //    57: lsub           
        //    58: aconst_null    
        //    59: nop            
        //    60: dstore          40
        //    62: astore_1        /* moduleName */
        //    63: fmul           
        //    64: ladd           
        //    65: fneg           
        //    66: ladd           
        //    67: laload         
        //    68: idiv           
        //    69: ladd           
        //    70: fdiv           
        //    71: dsub           
        //    72: laload         
        //    73: iastore        
        //    74: fadd           
        //    75: fmul           
        //    MethodParameters:
        //  Name        Flags  
        //  ----------  -----
        //  loader      
        //  moduleName  
        //    StackMapTable: 00 07 0E 40 01 47 01 FF 00 00 00 02 07 00 39 07 00 12 00 02 01 01 0C FC 00 1B 07 00 0B FC 00 04 07 00 0B
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void exportTo(final Class<?> client) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: fmul           
        //     3: ladd           
        //     4: fneg           
        //     5: ladd           
        //     6: laload         
        //     7: drem           
        //     8: lsub           
        //     9: dadd           
        //    10: lneg           
        //    11: frem           
        //    12: lmul           
        //    13: ineg           
        //    14: lshl           
        //    MethodParameters:
        //  Name    Flags  
        //  ------  -----
        //  client  
        //    Signature:
        //  (Ljava/lang/Class<*>;)V
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void exportFromTo(final Module truffleModule, final Module clientModule) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: fconst_1       
        //     3: nop            
        //     4: aconst_null    
        //     5: nop            
        //     6: nop            
        //     7: nop            
        //     8: iconst_2       
        //     9: nop            
        //    10: laload         
        //    11: nop            
        //    12: faload         
        //    13: nop            
        //    14: nop            
        //    15: nop            
        //    16: iconst_5       
        //    17: nop            
        //    18: daload         
        //    19: nop            
        //    20: aaload         
        //    21: nop            
        //    22: iconst_m1      
        //    23: nop            
        //    24: aload_2         /* packages */
        //    25: nop            
        //    26: nop            
        //    27: nop            
        //    28: saload         
        //    29: nop            
        //    30: nop            
        //    31: nop            
        //    32: iconst_m1      
        //    33: nop            
        //    34: nop            
        //    35: nop            
        //    36: aconst_null    
        //    37: return         
        //    38: nop            
        //    39: nop            
        //    40: nop            
        //    41: iconst_m1      
        //    42: nop            
        //    43: aload_3        
        //    44: nop            
        //    45: nop            
        //    46: nop            
        //    47: iconst_3       
        //    48: nop            
        //    49: aconst_null    
        //    50: nop            
        //    51: nop            
        //    52: nop            
        //    53: istore_3       
        //    54: nop            
        //    55: iaload         
        //    56: nop            
        //    57: nop            
        //    58: nop            
        //    59: lload           truffleModule
        //    61: iconst_m1      
        //    62: nop            
        //    MethodParameters:
        //  Name           Flags  
        //  -------------  -----
        //  truffleModule  
        //  clientModule   
        //    StackMapTable: 00 03 FD 00 11 07 00 49 07 00 4A 29 F9 00 02
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstOptimizer.introducePreIncrementForInstanceFields(AstOptimizer.java:1203)
        //     at com.strobel.decompiler.ast.AstOptimizer.introducePreIncrementOptimization(AstOptimizer.java:1029)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:63)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void addReads(final Class<?> client) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: dstore          client
        //     4: astore          client
        //     6: nop            
        //     7: nop            
        //     8: istore_0        /* client */
        //     9: nop            
        //    10: nop            
        //    11: nop            
        //    12: fconst_1       
        //    13: nop            
        //    14: aconst_null    
        //    15: nop            
        //    16: nop            
        //    17: nop            
        //    MethodParameters:
        //  Name    Flags  
        //  ------  -----
        //  client  
        //    Signature:
        //  (Ljava/lang/Class<*>;)V
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static <Service> List<Iterable<Service>> getTruffleRuntimeLoaders(final Class<Service> serviceClass) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: iconst_0       
        //     3: nop            
        //     4: aload_2        
        //     5: nop            
        //     6: nop            
        //     7: nop            
        //    MethodParameters:
        //  Name          Flags  
        //  ------------  -----
        //  serviceClass  
        //    Signature:
        //  <Service:Ljava/lang/Object;>(Ljava/lang/Class<TService;>;)Ljava/util/List<Ljava/lang/Iterable<TService;>;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static <S> void addUses(final Class<S> service) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: dstore_0        /* service */
        //     3: nop            
        //     4: iconst_m1      
        //     5: nop            
        //     6: nop            
        //     7: nop            
        //     8: dload_1         /* module */
        //     9: nop            
        //    10: dstore_1        /* module */
        //    11: nop            
        //    12: astore          service
        //    14: nop            
        //    15: nop            
        //    16: iconst_2       
        //    17: nop            
        //    18: fload_0         /* service */
        //    19: nop            
        //    20: dstore_2       
        //    MethodParameters:
        //  Name     Flags  
        //  -------  -----
        //  service  
        //    Signature:
        //  <S:Ljava/lang/Object;>(Ljava/lang/Class<TS;>;)V
        //    StackMapTable: 00 01 FC 00 14 07 00 0B
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static Object getUnnamedModule(final ClassLoader classLoader) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dconst_0       
        //     2: astore_3       
        //     3: goto            10
        //     6: astore          4
        //     8: aconst_null    
        //     9: areturn        
        //    10: aload_3        
        //    MethodParameters:
        //  Name         Flags  
        //  -----------  -----
        //  classLoader  
        //    StackMapTable: 00 01 06
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: 3
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2065)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static boolean verifyModuleVisibility(final Object module, final Class<?> memberClass) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: nop            
        //     4: dload_2        
        //     5: nop            
        //     6: iconst_m1      
        //     7: nop            
        //     8: bastore        
        //     9: nop            
        //    10: castore        
        //    11: nop            
        //    12: iconst_1       
        //    13: nop            
        //    14: nop            
        //    15: nop            
        //    16: faload         
        //    17: nop            
        //    18: dstore_1        /* memberClass */
        //    19: nop            
        //    20: astore          module
        //    22: nop            
        //    23: nop            
        //    24: iconst_3       
        //    25: nop            
        //    26: aload_0         /* module */
        //    27: nop            
        //    28: sastore        
        //    29: nop            
        //    30: caload         
        //    31: nop            
        //    32: aconst_null    
        //    33: nop            
        //    34: lconst_1       
        //    35: nop            
        //    36: dload_0         /* module */
        //    37: nop            
        //    38: pop            
        //    39: nop            
        //    40: caload         
        //    41: nop            
        //    42: iconst_m1      
        //    43: nop            
        //    44: aload_0         /* module */
        //    45: nop            
        //    46: iconst_3       
        //    47: nop            
        //    48: aastore        
        //    49: nop            
        //    50: astore          module
        //    52: iconst_0       
        //    53: nop            
        //    54: istore_0        /* module */
        //    55: nop            
        //    56: nop            
        //    57: nop            
        //    58: lload_2         /* lookupModule */
        //    59: nop            
        //    60: iconst_0       
        //    61: nop            
        //    62: fload_1         /* memberClass */
        //    63: nop            
        //    64: iconst_0       
        //    65: nop            
        //    66: aastore        
        //    67: nop            
        //    68: istore_1        /* memberClass */
        //    MethodParameters:
        //  Name         Flags  
        //  -----------  -----
        //  module       
        //  memberClass  
        //    Signature:
        //  (Ljava/lang/Object;Ljava/lang/Class<*>;)Z
        //    StackMapTable: 00 05 FC 00 0B 07 00 0B FC 00 0B 07 00 0B FC 00 1B 07 00 12 01 0D
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static boolean isNonTruffleClass(final Class<?> clazz) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aconst_null    
        //     2: nop            
        //     3: istore_0        /* clazz */
        //     4: nop            
        //     5: nop            
        //     6: nop            
        //     7: lload           clazz
        //     9: iconst_m1      
        //    10: nop            
        //    11: nop            
        //    12: nop            
        //    13: aload_1         /* truffleClassLoader */
        //    14: nop            
        //    15: ladd           
        //    16: nop            
        //    17: istore_1        /* truffleClassLoader */
        //    18: nop            
        //    19: nop            
        //    20: nop            
        //    21: nop            
        //    22: nop            
        //    23: aload_1         /* truffleClassLoader */
        //    24: nop            
        //    MethodParameters:
        //  Name   Flags  
        //  -----  -----
        //  clazz  
        //    Signature:
        //  (Ljava/lang/Class<*>;)Z
        //    StackMapTable: 00 02 FD 00 17 07 00 39 07 00 39 40 01
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void fullFence() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        // The error that occurred was:
        // 
        // com.strobel.assembler.metadata.MethodBodyParseException: An error occurred while parsing the bytecode of method 'com/oracle/truffle/api/impl/TruffleJDKServices.fullFence:()V'.
        //     at com.strobel.assembler.metadata.MethodReader.readBody(MethodReader.java:66)
        //     at com.strobel.assembler.metadata.MethodDefinition.tryLoadBody(MethodDefinition.java:729)
        //     at com.strobel.assembler.metadata.MethodDefinition.getBody(MethodDefinition.java:83)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:202)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // Caused by: java.lang.ClassCastException: com.strobel.assembler.ir.ConstantPool$MethodReferenceEntry cannot be cast to com.strobel.assembler.ir.ConstantPool$ConstantEntry
        //     at com.strobel.assembler.ir.ConstantPool.lookupConstant(ConstantPool.java:120)
        //     at com.strobel.assembler.metadata.ClassFileReader$Scope.lookupType(ClassFileReader.java:1225)
        //     at com.strobel.assembler.metadata.MethodReader.readBodyCore(MethodReader.java:186)
        //     at com.strobel.assembler.metadata.MethodReader.readBody(MethodReader.java:62)
        //     ... 17 more
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void acquireFence() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: nop            
        //     3: nop            
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void releaseFence() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: lmul           
        //     3: nop            
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void loadLoadFence() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: -3
        //     at com.strobel.assembler.flowanalysis.ControlFlowGraphBuilder.calculateIncomingJumps(ControlFlowGraphBuilder.java:122)
        //     at com.strobel.assembler.flowanalysis.ControlFlowGraphBuilder.build(ControlFlowGraphBuilder.java:96)
        //     at com.strobel.assembler.flowanalysis.ControlFlowGraphBuilder.build(ControlFlowGraphBuilder.java:60)
        //     at com.strobel.decompiler.ast.AstBuilder$FinallyInlining.preProcess(AstBuilder.java:4688)
        //     at com.strobel.decompiler.ast.AstBuilder$FinallyInlining.<init>(AstBuilder.java:4178)
        //     at com.strobel.decompiler.ast.AstBuilder$FinallyInlining.run(AstBuilder.java:4293)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:100)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void storeStoreFence() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aconst_null    
        //     2: nop            
        //     3: nop            
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: nop            
        //     3: aconst_null    
        //     4: nop            
        //     5: aload_2        
        //     6: nop            
        //     7: nop            
        //     8: nop            
        //     9: fload_1        
        //    10: nop            
        //    11: aconst_null    
        //    12: nop            
        //    13: nop            
        //    14: nop            
        //    15: nop            
        //    16: nop            
        //    StackMapTable: 00 02 0C 40 01
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
