filename = input("filename:")
valnumber = int(input("valnumber:"))
jsonText = "{"
for i in range(valnumber):
    r = input("R:")
    g = input("G:")
    b = input("B:")
    jsonText += ("\n\"segColor"
                 + str(i+1) + "\": [" + str(r) + ", " + str(g) + ", " + str(b) + "],")
jsonText +="  \n\"frameColor\": [0,255,0],\n\"isBarFilled\": true,\n\"hasBarFrame\": false,\n\"frameSize\": 0.02\n}"
f = open(filename+".json","w")
f.write(jsonText)
f.close()
