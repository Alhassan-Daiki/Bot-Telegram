import glob

repertoires = glob.glob('*')
repertoires.remove('clean.py')
print(repertoires)

for r in repertoires:
	files = glob.glob(r+'/*.java')
	for file in files:
		with open(file, 'r') as f:
			line = f.readline()
			result = ''
			while line:
				if '@' not in line and 'import' not in line and 'Automatically' not in line:
					#print(line)
					result += line
				line = f.readline()

		with open (file, 'w') as f:
			f.write(result)