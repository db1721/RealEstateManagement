package Project4;

/*
 * File: Project Name
 * Author: Dan Beck
 * Date: March 8, 2020
 * Purpose: Generic interface named StateChangeable and it should have a 
 * 			bounded generic type parameter whose type must be an enumerated 
 * 			type. It should contain one abstract method changeState that has a 
 * 			parameter whose type of the generic type parameter.
 */

public interface StateChangable 
{
	public abstract Status changeState(int st);
}
