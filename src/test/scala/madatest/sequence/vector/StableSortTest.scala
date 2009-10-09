

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest


import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._


class StableSortTest {
    def testTrivial {
        val actual = fromArray(example1).seal.stableSortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testImplicit {
        val actual = fromArray(example1).seal.stableSort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).stableSortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).stableSortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJList(fromArray(example1).toJList).stableSortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testStablity: Unit = {
        val ex1 = Array( 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4) // 15 elements
        val ex2 = Array(18, 8, 0,14,17,19, 4,15,12, 6,23, 0, 11, 4,13) // 15 elements
        val ex = vector.from(ex1).zip(ex2).copy
        ex.stableSortBy{ case (x, y) => x._1 < y._1 }
        assertTrue(mada.sequence.vector.stl.isSortedBy(ex, ex.start, ex.end){ case (x, y) => x._1 < y._1 })
        assertEquals( Vector(18,23), ex.filter{ x => x._1 == 0 }.map{ _._2 } )
        assertEquals( Vector(12,13), ex.filter{ x => x._1 == 4 }.map{ _._2 } )
    }

    def testStablity2: Unit = {
        val ex1 = Array(12,18,12,17,12, 8,12, 6,12,23, 0,12,15,12, 4) // 15 elements
        val ex2 = Array(18, 8, 0,14,17,19, 4,15,12, 6, 23,0,11, 4,13) // 15 elements
        val ex = vector.from(ex1).zip(ex2).copy
        ex.stableSortBy{ case (x, y) => x._1 < y._1 }
        assertTrue(mada.sequence.vector.stl.isSortedBy(ex, ex.start, ex.end){ case (x, y) => x._1 < y._1 })
        assertEquals( Vector(18,0,17,4,12,0,4), ex.filter{ x => x._1 == 12 }.map{ _._2 } )
    }


// vector.stl
    def testStlTrivial: Unit = {
        val v = fromArray(example1)
        mada.sequence.vector.stl.stableSortBy(v, 0, v.size){ _ < _ }
        assertEquals(fromArray(example1Sorted), v)
    }

    def testStlBy: Unit = {
        val v = fromArray(example1)
        mada.sequence.vector.stl.stableSortBy(v, 0, v.size){ _ > _ }
        assertEquals(fromArray(example1ReversedSorted), v)
    }

    def testStlEmpty: Unit = {
        val v = fromArray(empty1)
        mada.sequence.vector.stl.stableSortBy(v, 0, v.size){ _ < _ }
        detail.TestEmpty(v)
    }

    def longExample1 = Array(
        11,415,557,948,752,833,812,142,836,400,93,650,58,230,643,941,987,10,461,581,521,151,372,158,223,738,234,992,257,935,462,804,38,725,114,766,830,320,264,201,634,136,316,651,945,178,842,498,953,802,140,890,56,199,59,884,791,879,934,732,823,278,787,670,617,256,480,305,330,298,585,347,332,265,647,451,848,388,919,487,377,210,43,502,589,109,715,150,270,713,665,618,196,740,835,964,285,239,535,755,875,544,194,34,122,985,246,955,290,733,32,430,476,880,656,822,205,440,78,271,125,519,483,3,361,436,237,464,696,175,180,723,343,958,619,33,299,777,183,531,819,563,105,157,901,241,362,211,456,580,994,794,250,679,545,401,381,666,507,161,468,968,437,45,338,485,459,431,853,721,961,87,796,988,51,382,172,615,497,572,95,781,743,706,805,413,307,813,286,453,780,327,726,76,600,335,269,475,491,358,351,716,79,23,135,214,251,64,913,240,811,162,452,281,500,360,969,138,263,145,820,20,613,185,48,624,423,416,749,674,862,395,365,588,280,648,635,65,414,153,851,407,282,834,49,472,110,962,26,284,384,458,681,198,429,315,748,457,495,632,179,843,399,394,956,276,576,767,534,274,583,119,664,101,376,800,602,398,947,255,704,866,478,818,52,826,858,352,609,357,763,17,55,810,449,660,984,914,378,920,930,193,511,60,788,937,424,991,682,646,99,366,821,225,363,924,869,645,730,779,750,700,979,638,658,217,260,465,98,855,556,592,67,874,300,297,253,775,737,909,905,841,520,137,469,814,734,847,454,978,14,292,171,126,897,2,692,942,695,864,886,216,309,368,30,385,155,460,61,334,844,232,163,986,607,896,339,308,608,89,639,192,899,910,208,13,289,291,438,570,824,554,856,488,258,786,828,434,243,611,215,593,663,627,829,782,774,735,527,687,693,863,591,146,25,625,927,584,72,227,506,694,375,184,719,54,745,816,626,831,867,166,728,946,326,832,312,206,940,195,689,532,359,177,809,765,268,960,827,404,499,5,406,970,467,720,432,857,724,91,121,341,369,510,209,729,630,496,191,699,117,428,601,573,154,536,116,892,555,759,610,212,207,539,19,921,893,736,637,329,473,68,139,542,653,411,903,569,248,655,267,758,840,75,756,132,421,344,739,419,200,371,141,486,537,915,845,15,685,492,995,262,354,938,12,189,744,392,90,53,131,746,668,509,900,356,220,229,929,50,975,408,325,709,631,649,894,889,628,549,493,426,111,640,852,881,129,808,950,654,353,798,188,771,629,789,783,558,380,143,383,803,548,322,311,523,564,261,317,144,559,62,370,242,466,24,57,971,295,701,773,518,888,529,742,998,236,113,999,967,525,133,908,44,501,165,543,100,538,31,203,697,711,231,526,373,213,396,760,238,508,28,550,36,877,254,181,417,850,846,324,397,405,259,579,228,127,596,633,667,778,420,517,37,474,304,690,652,39,595,164,118,982,222,939,134,974,616,219,747,16,582,84,604,40,176,983,83,972,425,512,306,169,514,435,669,115,409,503,644,686,718,197,333,587,641,606,446,885,990,302,374,761,904,249,568,403,598,82,622,303,288,981,112,513,671,792,410,702,226,541,684,868,605,66,443,379,561,86,18,996,97,9,952,186,8,906,27,612,912,793,484,708,318,965,471,976,872,245,710,310,63,92,785,722,768,187,221,283,470,266,590,865,482,463,156,516,427,104,418,762,764,533,149,623,218,741,244,636,391,182,148,481,917,661,925,107,233,336,911,954,815,445,751,120,103,790,553,876,296,691,77,936,565,871,837,882,173,364,447,505,873,301,489,174,795,170,776,128,159,345,348,167,314,479,522,757,21,784,698,448,614,599,190,799,524,47,675,993,642,294,870,951,928,477,123,386,676,277,35,328,450,854,94,402,849,807,69,490,980,683,603,355,313,331,878,287,825,6,130,224,102,916,124,597,279,337,147,433,963,677,620,801,106,898,727,926,272,439,252,594,659,494,957,883,717,168,966,323,350,552,753,387,817,504,680,88,562,273,412,367,895,0,578,712,575,770,707,71,152,42,74,441,949,891,319,703,204,714,1,797,943,540,96,455,235,70,293,657,346,85,861,859,586,839,340,41,515,932,530,81,907,944,923,688,922,673,997,902,838,349,973,754,444,393,705,931,662,769,989,108,46,442,977,621,321,918,528,275,342,547,959,546,551,160,887,567,560,4,577,389,860,566,80,202,247,571,73,678,772,806,731,7,22,574,933,422,29,390,672
    )

    def testStlLongExample1: Unit = {
        val v = fromArray(longExample1)
        mada.sequence.vector.stl.stableSortBy(v, 0, v.size){ _ < _ }
        assertEquals(vector.range(0, 1000), v)
    }
}
