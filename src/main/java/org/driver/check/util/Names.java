package org.driver.check.util;

public class Names {
	
	private static final String[] firstNames = {
		"Muoi",
		"Ayako",
		"Marquetta",
		"Dulce",
		"Joey",
		"Cayla",
		"Kitty",
		"Elsa",
		"Iris",
		"Janeen",
		"Lillia",
		"Dedra",
		"Melida",
		"Verna",
		"Martha",
		"Li",
		"Rueben",
		"Kelsey",
		"Johanna",
		"Rozella"
	};
	
	private static final String[] lastNames = {
		"Mellinger",
		"Beard",
		"Sterling",
		"Nadel",
		"Rio",
		"Collin",
		"Staten",
		"Voris",
		"Kush",
		"Laduke",
		"Rumore",
		"Quinn",
		"Hoffmann",
		"Marc",
		"Rodrigez",
		"Bonet",
		"Rhoda",
		"Mcfaul",
		"Graffam",
		"Sanabria"			
	};	
	
	private static final String[] phoneNumbers = {
"(899) 151-3728",
"(855) 266-1808",
"(855) 112-4794",
"(855) 454-4795",
"(811) 461-2905",
"(855) 723-5318",
"(844) 319-1132",
"(833) 870-6655",
"(855) 274-2804",
"(833) 281-6000",
"(844) 294-3379",
"(855) 499-1443",
"(822) 289-1847",
"(855) 297-3986",
"(855) 815-1943",
"(844) 325-8796",
"(899) 752-6098",
"(855) 866-8420",
"(855) 464-3177",
"(844) 033-8298"
	};

	public static void main(String[] args) {
	}
	
	public static String getRandomFirstName(){
		return firstNames[RandomDC.getRandomInt(20)];
	}
	
	public static String getRandomLasstName(){
		return lastNames[RandomDC.getRandomInt(20)];
	}
	
	public static String getRandomPhoneNumber(){
		return phoneNumbers[RandomDC.getRandomInt(20)];
	}
}
