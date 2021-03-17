package Project4;

/*
 * File: Project Name
 * Author: Dan Beck
 * Date: March 8, 2020
 * Purpose: Enumerated type should be named Status and 
 * 			should contain three enumeration literals, FOR_SALE, 
 * 			UNDER_CONTRACT and SOLD.
 */

enum Status implements StateChangable
{
	FOR_SALE
	{
		@Override
		public Status changeState(int st) 
		{
			return FOR_SALE;
		}
	}, 
	UNDER_CONTRACT
	{
		@Override
		public Status changeState(int st) 
		{
			return UNDER_CONTRACT;
		}
	},
	SOLD
	{
		@Override
		public Status changeState(int st) 
		{
			return SOLD;
		}
	};
}
