//ChessBoard
import java.io.*;
import java.util.*;
import java.lang.*;

class functioning {
	public static int pwstatus[] = { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ;
	public static int pbstatus[] = { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ;

	public static char board[][] = {{ 'R' , 'H' , 'B' , 'K' , 'Q' , 'B' , 'H' , 'R' },{ 'P' , 'P' , 'P' , 'P' , 'P' , 'P' , 'P' , 'P' },{ ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' },{ ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' },{ ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' },{ ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' },{ 'p' , 'p' , 'p' , 'p' , 'p' , 'p' , 'p' , 'p' },{ 'r' , 'h' , 'b' , 'k' , 'q' , 'b' , 'h' , 'r' }};

	static Scanner in = new Scanner(System.in);
	
	public static void display() {
		int i , j , k;
		System.out.print(" ");
		for(i=0; i<8; i++) 
			System.out.print("    "+i); 
		System.out.print("\n") ;
		for(k=0; k<8; k++) {
			System.out.print("  ");
			for( i=0 ; i<42 ; i++ )  
				System.out.print("-");  
			System.out.print("\n"); 
			System.out.print(k+" ");
			for( j=0 ; j<8 ; j++ )  
				System.out.print("|| "+board[k][j]+" "); 
			System.out.print("|| \n");
		}
		System.out.print("  ");
		for( i=0 ; i<42 ; i++ )  
			System.out.print("-");  
		System.out.print("\n");
	}
	
	public static void change(int r1, int c1, int r2, int c2) {
		char temp;
		temp = board[r1][c1];
		//board[r1][c1] = board[r2][c2];
		board[r1][c1] = ' ';
		board[r2][c2] = temp;
	}
	
	public static void pawn(int r1, int c1) {
		pwstatus[c1]++;
		System.out.print("Available are: \n");
		if(pwstatus[c1] == 1) {
			if(board[r1+1][c1] == ' ')
				System.out.print((r1+1)+""+c1+" , ");
			if(board[r1+2][c1] == ' ')
				System.out.print((r1+2)+""+c1+" , ");
		}
		else {
			if(board[r1+1][c1] == ' ' )
				System.out.print((r1+1)+""+c1+" , ");
			if(check(r1+1 , c1+1) == 1 )
				System.out.print((r1+1)+""+(c1+1)+" , ");
			if(check(r1+1 , c1-1) == 1 )
				System.out.print((r1+1)+""+(c1-1)+" , ");
		}
	}
	
	public static void rook(int r1, int c1) {
		int i, j, n;
		System.out.print("Available are: \n");
		n=c1;
		System.out.print("Horizontally: \n");
		while(board[r1][n-1] == ' ') {
			if(n == 0) 
				break; 
			System.out.print(r1+""+(n-1)+" , ");
			n--;
		}
		n=c1;
		while((board[r1][n+1] == ' ')  && ((n+1) <= 7)) {
			System.out.print(r1+""+(n+1)+" , ");
			++n;
		}
		System.out.print("\nVertically:\n");
		n = r1;
		while((board[n-1][c1] == ' ') && (n > -1 )) {
			System.out.print((n-1)+""+c1+" , ");
			--n;
		}
		n = r1;
		while((board[n+1][c1] == ' ') && ( (n) <= 7 )) {
			System.out.print((n+1)+""+c1+" , ");
			++n;
		}
	}
	
	public static void horse(int r1, int c1) {
		System.out.print("Available are: ");
		if(board[r1+2][c1+1] == ' ') 
			System.out.print((r1+2)+""+(c1+1)+", ");
		if(board[r1+2][c1-1] == ' ') { 
			if((c1-1) > -1) 
				System.out.print((r1+2)+""+(c1-1)+", ") ; 
		}
		if(board[r1+1][c1+2] == ' ') {  
			if((c1+2) != 8) 
				System.out.print((r1+1)+""+(c1+2)+", ") ; 
		}
		if(board[r1-1][c1+2] == ' ')   
			System.out.print((r1-1)+""+(c1+2)+", ") ; 
		if(board[r1-2][c1-1] == ' ') {
			if((c1-1) != -1)
				System.out.print((r1-2)+""+(c1-1)+", ") ;
		}
		if(board[r1-2][c1+1] == ' ') 
			System.out.print((r1-2)+""+(c1+1)+", ") ;
		if(board[r1+1][c1-2] == ' ') 
			System.out.print((r1+1)+""+(c1-2)+", ") ;
		if(board[r1-1][c1-2] == ' ') {
			if((c1-2) != -1)
				System.out.print((r1-1)+""+(c1-2)+", ") ;
		}
	}
	
	public static void bishop(int r1, int c1) {
		int a, b;
		System.out.print("Available are: \n");
		a = 1; b = 1;
		while(board[r1-a][c1+b] == ' ') {
			if( (r1-a) == -1 || (c1+b) == 8 ) 
				break ;
			System.out.print((r1-a)+""+(c1+b)+" , ") ;
			a++; b++;
		}
		a = 1; b = 1;
		while(board[r1+a][c1-b] == ' ') {
			if( (r1+a) == 8 || (c1-b) == -1 ) 
				break ;
			System.out.print((r1+a)+""+(c1-b)+" , ") ;
			a++; b++;
		}
		a = 1; b = 1;
		while(board[r1+a][c1+b] == ' ') {
			if( (r1+a) == 8 || (c1+b) == 8 ) 
				break ;
			System.out.print((r1+a)+""+(c1+b)+" , ") ;
			a++; b++;
		}
		a = 1; b = 1;
		while(board[r1-a][c1-b] == ' ') {
			if( (r1-a) == -1 || (c1-b) == -1 ) 
				break ;
			System.out.print((r1-a)+""+(c1-b)+" , ") ;
			a++; b++;
		}
	}
	
	public static void king(int r1, int c1) {
		System.out.print("Available are: ") ;
		if(board[r1][c1+1] == ' ') 
			System.out.print(r1+""+(c1+1)+" , ") ;
		if(board[r1][c1-1] == ' ') 
			System.out.print(r1+""+(c1-1)+" , ") ;
		if(board[r1+1][c1] == ' ') 
			System.out.print((r1+1)+""+c1+" , ") ;
		if(board[r1-1][c1] == ' ') 
			System.out.print((r1-1)+""+c1+" , ") ;
		if(board[r1+1][c1+1] == ' ') 
			System.out.print((r1+1)+""+(c1+1)+" , ") ;
		if(board[r1-1][c1-1] == ' ') 
			System.out.print((r1-1)+""+(c1-1)+" , ") ;
		if(board[r1-1][c1+1] == ' ') 
			System.out.print((r1-1)+""+(c1+1)+" , ") ;
		if(board[r1+1][c1-1] == ' ') 
			System.out.print((r1+1)+""+(c1-1)+" , ") ;
	}
	
	public static void queen(int r1, int c1) {
		int x=1, y=1, a, b;
		System.out.print("Available are: ");
		System.out.print("Horizontal: ");
		while(board[r1][c1-y] == ' ') {
			if( (c1-y) == -1 ) 
				break;
			System.out.print(r1+""+(c1-y)+" , ");
			y++;
		}
		y = 1;
		while( board[r1][c1+y] == ' ' ) {
			if( (c1+y) == 8 ) 
				break;
			System.out.print(r1+""+(c1+y)+" , ");
			y++;
		}
		System.out.print("Vertical: ") ;
		x = 1 ;
		while(board[r1-x][c1] == ' ') {
			if( (r1-x) == -1 ) 
				break;
			System.out.print((r1-x)+""+c1+" , ");
			x++;
		}
		x = 1 ;
		while( board[r1+x][c1] == ' ' ) {
			if( (r1+x) == 8 ) 
				break;
			System.out.print((r1+x)+""+c1+" , ");
			x++;
		}
		System.out.print("Diagonally: ") ;
		a = 1; b = 1 ;
		while( board[r1-a][c1+b] == ' ' ) {
			if( (r1-a) == -1 || (c1+b) == 8 ) 
				break;
			System.out.print((r1-a)+""+(c1+b)+" , ");
			a++; b++;
		}
		a = 1; b = 1 ;
		while( board[r1+a][c1-b] == ' ' ) {
			if( (r1+a) == 8 || (c1-b) == -1 ) 
				break;
			System.out.print((r1+a)+""+(c1-b)+" , ");
			a++; b++;
		}
		a = 1; b = 1 ;
		while( board[r1+a][c1+b] == ' ' ) {
			if( (r1+a) == 8 || (c1+b) == 8 ) 
				break ;
			System.out.print((r1+a)+""+(c1+b)+" , ");
			a++; b++;
		}
		a = 1; b = 1;
		while( board[r1-a][c1-b] == ' ' ) {
			if( (r1-a) == -1 || (c1-b) == -1 ) 
				break;
			System.out.print((r1-a)+""+(c1-b)+" , ");
			a++; b++;
		}
	}
	
	public static void pawnb( int r1 , int c1 ) { // paido black
		pbstatus[c1]++;
		System.out.print("Available are: \n") ;
		if( pbstatus[c1] == 1 ) {
			if( board[r1-1][c1] == ' ' )
				System.out.print((r1-1)+""+c1+" , ") ;
			if( board[r1-2][c1] == ' ' )
				System.out.print((r1-2)+""+c1+" , ") ;
		}
		else {
			if(board[r1-1][c1] == ' ' )
				System.out.print((r1-1)+""+c1+" , ") ;
			if( check2(r1-1 , c1-1) == 1 )
				System.out.print((r1-1)+""+(c1-1)+" , ") ;
			if( check2(r1-1 , c1+1) == 1 )
				System.out.print((r1-1)+""+(c1+1)+" , ") ;
		}
	}
	
	public static void player1() {
		int p1 , p2 , c1=0 , r1=0 , c2 , r2, again1=0;
		System.out.print("\nPLAYER 1 - Big Case\n") ;
		while(again1 == 0) {
			++again1;
			System.out.print("\nEnter Position of Element to change ( RC ): ") ;	
			p1 = in.nextInt();
			c1 = p1 % 10 ;
			r1 = p1 / 10 ;
			switch(board[r1][c1]) {
				case 'P': pawn( r1 , c1 );
							break;
				case 'R': rook( r1 , c1 );
							break;
				case 'H': horse( r1 , c1 );
							break;
				case 'B': bishop( r1 , c1 );
							break;
				case 'K': king( r1 , c1 );
							break;
				case 'Q': queen( r1 , c1 );
							break;
				default: System.out.print("Invalid Position ! "); again1=0;
							break;
			}
		}
		System.out.print("\nEnter Position of Place to Send ( RC ): ") ;
		p2 = in.nextInt();
		c2 = p2 % 10 ;
		r2 = p2 / 10  ;
		change(r1,c1,r2,c2) ;
	}
	
	public static void player2() {
		int p1 , p2 , c1=0 , r1=0 , c2 , r2, again2=0;
		System.out.print("\nPLAYER 2 - Small Case \n") ;
		while(again2 == 0) {
			++again2;
			System.out.print("\nEnter Position of Element to change ( RC ): ") ;
			p1 = in.nextInt();
			c1 = p1 % 10 ;
			r1 = p1 / 10 ;
			switch( board[r1][c1] ) {
				case 'p': pawnb( r1 , c1 );
							break;
				case 'r': rook( r1 , c1 );
							break;
				case 'h': horse( r1 , c1 );
							break;
				case 'b': bishop( r1 , c1 );
							break;
				case 'k': king( r1 , c1 );
							break;
				case 'q': queen( r1 , c1 );
							break;
				default: System.out.print( "Invalid Position ! " ); again2=0; 
							break;
			}
		}
		System.out.print("\nEnter Position of Place to Send ( RC ): ") ;
		p2 = in.nextInt();
		c2 = p2 % 10 ;
		r2 = p2 / 10  ;
		change(r1,c1,r2,c2) ;
	}
	
	public static int check(int x, int y) {
		int num=0;
		switch( board[x][y] ) {
			case 'p':
			case 'r':
			case 'h':
			case 'b':
			case 'k':
			case 'q': num=1; 
						break ;
			default: num=0 ;
		}
		return num;
	}
	
	public static int check2(int x, int y) {
		int num = 0;
		switch( board[x][y] ) {
			case 'P':
			case 'R':
			case 'H':
			case 'B':
			case 'K':
			case 'Q': num=1; 
						break ;
			default: num=0 ;
		}
		return num;
	}
	
}

public class ChessBoard {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		functioning fn = new functioning();
		int x = 0;
		String ch;
		System.out.print("   _____ _    _ ______  _____ _____ \n  / ____| |  | |  ____|/ ____/ ____| \n | |    | |__| | |__  | (___| (___  \n | |    |  __  |  __|  \\___  \\___ \\ \n | |____| |  | | |____ ____) |___) | \n  \\_____|_|  |_|______|_____/_____/ \n");
		System.out.print( "\n\tWELCOME TO CHESS GAME" ) ;
		System.out.print( "\n\n\t By Kaushiki Bhattacharya " );
		ch = in.nextLine();
		do {
			x++;
			fn.display();
			if( (x%2) == 0 ) 
				fn.player2();
			else
				fn.player1();
			System.out.print( " \n\nPress Enter To Continue ! \n\n " ) ;
			ch = in.nextLine();
		}while( ch.isEmpty() == true );
	}
}
