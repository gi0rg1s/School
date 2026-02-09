word = input("Write a word: ")
chars = {}

for c in word:
    if(c not in chars):
        chars[c] = 1
    else:
        chars[c] += 1
print(chars)