books = [
    {"title": "1984", "author": "Orwell", "year": 1949},
    {"title": "Dune", "author": "Herbert", "year": 1965},
    {"title": "Foundation", "author": "Asimov", "year": 1951}
    ]

print("books into the library: ")
for book in books:
    print(book["title"])
    
print("\nbooks published before 1960 : ")
for book in books:
    if book["year"] < 1960:
        print(book["title"])
    
title = input("insert the book title : ")
flag = False
for book in books:
    if book["title"] == title:
        print("The author is: ", book["author"])
        flag = True
        
if flag == False:
    print("title not fount")