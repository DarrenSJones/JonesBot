package ca.darrensjones.jonesbot.test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-21
 * @since 1.0.0 2020-11-21
 */
public class BotTestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String[] s = result.getInstanceName().split("\\.");
		String suite = s[s.length - 2];
		String set = s[s.length - 1];
		String test = result.getMethod().getMethodName();
		Reporter.debug(String.format("[FAIL] %s: %s: %s", suite, set, test));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String[] s = result.getInstanceName().split("\\.");
		String suite = s[s.length - 2];
		String set = s[s.length - 1];
		String test = result.getMethod().getMethodName();
		Reporter.debug(String.format("[PASS] %s: %s: %s", suite, set, test));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}