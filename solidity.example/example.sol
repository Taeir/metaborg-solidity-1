	contract two {
		struct structest {
			int a;
			bool b;
		}
		
		int x;
		int y;
		int z;
		structest what;
		
		function testone(int test) public returns (int){
			if(x < test){
				what.a = y;
			}
			else{
				what.a = z;
			}
			
			return test;
		}
	}