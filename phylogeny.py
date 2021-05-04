from Bio.Align.Applications import ClustalwCommandline
from Bio import SeqIO
from Bio import Phylo
from Bio.Phylo.TreeConstruction import DistanceCalculator
from Bio import AlignIO
import pylab
import csv

def phyl_tree(s):
    clustalw_exe = r"C:\Program Files (x86)\ClustalW2\clustalw2.exe"
    with open(s, 'r') as firstfile, open('seq.fasta', 'a') as secondfile:
        for line in firstfile:
            secondfile.write(line)
    clustalw_cline = ClustalwCommandline(clustalw_exe, infile="seq.fasta")
    stdout, stderr = clustalw_cline()

    align = AlignIO.read("seq.aln", "clustal")
    records = SeqIO.parse("seq.aln", "clustal")
    count = SeqIO.write(records, "msa.phylip", "phylip")

    calculator = DistanceCalculator('blosum62')
    dm = calculator.get_distance(align)
    with open('distMat.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(dm)
    # print(dm)

    tree = Phylo.read("seq.dnd", "newick")
    Phylo.draw(tree, do_show=False)
    pylab.axis('off')
    pylab.savefig('tree.svg', format='svg', bbox_inches='tight', dpi=300)
    return ['msa.phylip', 'distMat.csv', 'tree.svg']


