
//import org.eclipse.cdt.core.dom.ICodeReaderFactory.*;
//import org.eclipse.cdt.core.dom.ast.ASTVisitor;
//import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
//import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
//import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.DefaultLogService;
import org.eclipse.cdt.core.parser.IParserLogService;
//import org.eclipse.cdt.core.parser.IScannerInfo;
//import org.eclipse.cdt.core.parser.ScannerInfo;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IParserLogService log = new DefaultLogService();
	//	char[] code = readFile("C:\\grep.cpp");
		
	//	CodeReader reader = new CodeReader(code);
		
		
		
		
	//	Map definedSymbols = new HashMap();
	//	String[] includePaths = new String[0];
	//	IScannerInfo info = new ScannerInfo(definedSymbols, includePaths);
	//	ICodeReaderFactory readerFactory = FileCodeReaderFactory.getInstance();
	//	IASTTranslationUnit translationUnit = GPPLanguage.getDefault()
	//	.getASTTranslationUnit(reader, info, readerFactory, null, log);
	//	ASTVisitor visitor = new ImplASTVisitor();
	//	translationUnit.accept(visitor);
		System.out.println("Some notererer so funny text");
	}

}
