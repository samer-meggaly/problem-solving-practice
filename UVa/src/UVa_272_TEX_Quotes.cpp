//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>

int tex_quotes() {
	char output[1 << 20];
	char* line = NULL;
	size_t sz = 0;
	int oc = 0;
	int quote = 0;
	while(!feof(stdin)) {
		ssize_t ln = getline(&line, &sz, stdin);
		if(ln > 0) {
			int c;
			for(c = 0; c < ln; c++) {
				if(line[c] == '"' && quote == 0) {
					output[oc++] = '`';
					output[oc++] = '`';
					quote ^= 1;
				} else if (line[c] == '"' && quote == 1) {
					output[oc++] = '\'';
					output[oc++] = '\'';
					quote ^= 1;
				} else {
					output[oc++] = line[c];
				}
			}
		}
	}
	printf("%s", output);
	return EXIT_SUCCESS;
}
