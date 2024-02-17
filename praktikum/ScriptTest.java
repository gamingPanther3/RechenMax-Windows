package praktikum2;

import org.graalvm.polyglot.*;
import com.oracle.truffle.js.scriptengine.*;

public class ScriptTest
{
    public static void main(final String[] args) throws Exception {
        final GraalJSScriptEngine engine = GraalJSScriptEngine.create(Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build(), Context.newBuilder("js").allowIO(false).option("js.ecmascript-version", "2022"));
        final String data = "1+2+3+4+5+6+7+8+9+0";
        System.out.println("Calculate: 1 + 2 + data");
        System.out.println("  = " + engine.eval("1+2+1+2+3+4+5+6+7+8+9+0"));
    }
}
