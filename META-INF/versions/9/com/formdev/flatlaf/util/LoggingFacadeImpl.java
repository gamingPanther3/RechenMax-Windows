package com.formdev.flatlaf.util;

class LoggingFacadeImpl implements LoggingFacade
{
    private static final System.Logger LOG;
    
    LoggingFacadeImpl() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: invokevirtual   java/lang/System$Logger.log:(Ljava/lang/System$Logger$Level;Ljava/lang/String;Ljava/lang/Throwable;)V
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void logSevere(final String message, final Throwable t) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: iconst_m1      
        //     3: nop            
        //     4: aconst_null    
        //     5: nop            
        //     6: iload_1         /* message */
        //     7: nop            
        //     8: lload           this
        //    10: aconst_null    
        //    11: nop            
        //    12: bipush          0
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void logConfig(final String message, final Throwable t) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: nop            
        //     2: iconst_5       
        //     3: nop            
        //     4: iload_2         /* t */
        //     5: nop            
        //     6: dconst_1       
        //     7: nop            
        //     8: aconst_null    
        //     9: nop            
        //    10: bipush          0
        //    12: nop            
        //    13: nop            
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        //     at com.strobel.assembler.ir.attributes.CodeAttribute.<init>(CodeAttribute.java:60)
        //     at com.strobel.assembler.metadata.MethodDefinition.tryLoadBody(MethodDefinition.java:700)
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
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
